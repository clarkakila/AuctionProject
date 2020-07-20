package com.auction.demo.request;

import com.auction.demo.Item;
import com.auction.demo.dbo.AuctionItem;
import com.fasterxml.jackson.annotation.JsonProperty;


public class AuctionItemRequest {

    @JsonProperty("reservePrice")
    private double reservePrice;
    @JsonProperty("item")
    private Item item;

    public double getReservePrice(){
        return this.reservePrice;
    }

    public void setReservePrice(double reservePrice) {
        this.reservePrice = reservePrice;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem(){
        return this.item;
    }


}


