package com.auction.demo.request;

public class BidRequest {


    	private int auctionItemId;
    	private double maxAutoBidAmount;
    	private String bidderName;

	public int getAuctionItemId() {
		return auctionItemId;
	}

	public void setAuctionItemId(int auctionItemId) {
		this.auctionItemId = auctionItemId;
	}

	public double getMaxAutoBidAmount() {
		return maxAutoBidAmount;
	}

	public void setMaxAutoBidAmount(double maxAutoBidAmount) {
		this.maxAutoBidAmount = maxAutoBidAmount;
	}

	public String getBidderName() {
		return bidderName;
	}

	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}
}
