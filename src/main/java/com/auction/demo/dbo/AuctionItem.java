package com.auction.demo.dbo;

import com.auction.demo.request.AuctionItemRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Data @ToString
public class AuctionItem {

    @Id
    private int auctionItemId;
    private double reservePrice;
    private String itemId;
    private double currentBid;
    private String description;
    private String bidderName;

    public AuctionItem(AuctionItemRequest auctionItemRequest){
        this.reservePrice = auctionItemRequest.getReservePrice();
        this.itemId = auctionItemRequest.getItem().getItemId();
        this.description = auctionItemRequest.getItem().getDescription();
    }

    public AuctionItem(int auctionItemId, double currentBid, String bidderName) {
        this.auctionItemId = auctionItemId;
        this.currentBid = currentBid;
        this.bidderName = bidderName;
    }

    public int getAuctionItemId() {
        return auctionItemId;
    }

    public void setAuctionItemId(int auctionItemId) {
        this.auctionItemId = auctionItemId;
    }

    public double getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(double reservePrice) {
        this.reservePrice = reservePrice;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(double currentBid) {
        this.currentBid = currentBid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBidderName() {
        return bidderName;
    }

    public void setBidderName(String bidderName) {
        this.bidderName = bidderName;
    }

    public AuctionItem() {
        this.auctionItemId = 0;
        this.currentBid = 0;
        this.bidderName = null;
        this.description = null;
        this.itemId = null;
        this.reservePrice = 0;
    }
}
