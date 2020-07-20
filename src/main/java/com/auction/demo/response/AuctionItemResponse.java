package com.auction.demo.response;

import com.auction.demo.dbo.AuctionItem;

public class AuctionItemResponse {

    private int auctionItemId;

    public AuctionItemResponse(int auctionItemId) {
        this.auctionItemId = auctionItemId;
    }

    public AuctionItemResponse() {
    }

    public AuctionItemResponse(AuctionItem auctionItem){
        this.auctionItemId = auctionItem.getAuctionItemId();

    }
}
