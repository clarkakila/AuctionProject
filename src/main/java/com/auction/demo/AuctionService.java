package com.auction.demo;

import com.auction.demo.request.AuctionItemRequest;
import com.auction.demo.request.BidRequest;
import com.auction.demo.response.AuctionItemResponse;
import com.auction.demo.dbo.AuctionItem;
import com.auction.demo.dbo.AuctionItemRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuctionService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuctionService.class);

    private final AuctionItemRepo auctionItemRepo;

    public AuctionService(AuctionItemRepo auctionItemRepo){

        this.auctionItemRepo = auctionItemRepo;
    }

    public AuctionItemResponse createAuctionItem(AuctionItemRequest auctionItemRequest) {

        AuctionItemResponse auctionItemResponse;
        AuctionItem newAuctionItem = new AuctionItem(auctionItemRequest);
        AuctionItem auctionItem = null;

        try {
            auctionItem = auctionItemRepo.save(newAuctionItem);

        } catch (Exception ex) {
            LOGGER.error("failed to create auction item", ex);
        }

        return new AuctionItemResponse(auctionItem);
    }

    public Iterable<AuctionItem> getAuctionItems(){
        Iterable<AuctionItem> items = null;
        try {
            items = auctionItemRepo.findAll();

        } catch (Exception ex) {
            LOGGER.error("failed to get auction items", ex);
        }
        return items;
    }

    public Optional<AuctionItem> getAuctionItem(int auctionId){
        Optional<AuctionItem> item = null;
        try {
            item = auctionItemRepo.findById(auctionId);

        } catch (Exception ex) {
            LOGGER.error("failed to get auction items", ex);
        }
        return item;
    }


    public void bidOnItem(BidRequest bid) throws Exception {

        Optional<AuctionItem> auctionItem;
       auctionItem = auctionItemRepo.findById(bid.getAuctionItemId());

       if(auctionItem != null){

           if(bid.getMaxAutoBidAmount() < auctionItem.get().getReservePrice()){
               auctionItem.get().setCurrentBid(bid.getMaxAutoBidAmount());
               LOGGER.debug("bid has been placed for item " + auctionItem.get().getItemId()+" with bid price "+bid.getMaxAutoBidAmount());
               throw new Exception("bidder has not met the reserve price");
           }
           else if(bid.getMaxAutoBidAmount() > auctionItem.get().getReservePrice()){

               if(bid.getMaxAutoBidAmount() > auctionItem.get().getCurrentBid()+1.00 && auctionItem.get().getBidderName() != null){

                   LOGGER.debug("bid has been placed for item" + auctionItem.get().getItemId()+"with bid price"+bid.getMaxAutoBidAmount());
                   LOGGER.debug("bidder "+ auctionItem.get().getBidderName() + " has been over bid by " + bid.getBidderName());

                   String oldBidder = auctionItem.get().getBidderName();
                   auctionItem.get().setCurrentBid(bid.getMaxAutoBidAmount());
                   auctionItem.get().setBidderName(bid.getBidderName());

                   throw new Exception("bidder "+ oldBidder + " has been over bid");

               }
               else if(bid.getMaxAutoBidAmount() > auctionItem.get().getCurrentBid()+1.00 ){
                   auctionItem.get().setCurrentBid(bid.getMaxAutoBidAmount());
                   auctionItem.get().setBidderName(bid.getBidderName());
                   LOGGER.debug("bid has been placed for item " + auctionItem.get().getItemId()+" with bid price "+bid.getMaxAutoBidAmount());

               }
           }

         auctionItemRepo.save(auctionItem.get());
       }
       else{
           throw new Exception("auction item not found");
       }


    }
}
