package com.auction.demo;

import com.auction.demo.dbo.AuctionItem;
import com.auction.demo.request.AuctionItemRequest;
import com.auction.demo.request.BidRequest;
import com.auction.demo.response.AuctionItemResponse;
import com.auction.demo.response.GetAuctionItemsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.log.LogMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class AuctionController {

    AuctionService auctionService;
    final static Logger LOGGER = LoggerFactory.getLogger(AuctionController.class);


    @PostMapping(value = "/auctionItems", produces = APPLICATION_JSON_VALUE )
    public @ResponseBody
    ResponseEntity<AuctionItemResponse> createAuctionItem(@RequestBody AuctionItemRequest auctionItemRequest){

        AuctionItemResponse auctionItemResponse = null;

        if(auctionItemRequest != null){
            try{
               auctionItemResponse = auctionService.createAuctionItem(auctionItemRequest);
            }
            catch (Exception ex){
                LOGGER.error("failed to create auction item", ex);
            }
    }
        return ResponseEntity.ok(auctionItemResponse);
    }

    @GetMapping(value = "/auctionItems", produces = APPLICATION_JSON_VALUE )
    public @ResponseBody
    ResponseEntity<Iterable<AuctionItem>> getAuctionItems(){

        Iterable<AuctionItem> auctionItemsResponse = null;

            try{
                auctionItemsResponse = auctionService.getAuctionItems();
            }
            catch (Exception ex){
                LOGGER.error("failed to get auction items", ex);
            }
        return ResponseEntity.ok(auctionItemsResponse);
    }

    @GetMapping(value = "/auctionItems/{auctionItemId}", produces = APPLICATION_JSON_VALUE )
    public @ResponseBody
    ResponseEntity<Optional<AuctionItem>> getAuctionItems(@PathVariable("auctionItemId") int auctionId){

        Optional<AuctionItem> auctionItemsResponse = null;


        try{
            auctionItemsResponse = auctionService.getAuctionItem(auctionId);
        }
        catch (Exception ex){
            LOGGER.error("failed to get auction item", ex);
        }
        return ResponseEntity.ok(auctionItemsResponse);
    }

    @PostMapping(value = "/bids", produces = APPLICATION_JSON_VALUE )
    public @ResponseBody
    ResponseEntity bid(@RequestBody BidRequest bid){

        AuctionItemResponse auctionItemResponse = null;

        if(bid != null){
            try{
                auctionService.bidOnItem(bid);
            }
            catch (Exception ex){
                LOGGER.error("failed to create auction item", ex);
            }
            return ResponseEntity.ok(auctionItemResponse);
        }
        else
            return (ResponseEntity) ResponseEntity.badRequest();

    }
}
