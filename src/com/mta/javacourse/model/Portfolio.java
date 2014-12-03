package com.mta.javacourse.model;

import java.util.Date;

public class Portfolio {

	private String title;
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock [] stocks = new Stock[MAX_PORTFOLIO_SIZE];
	private StockStatus [] stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	private int portfolioSize;
	private int i;
	
	public class StockStatus{
		
		private String symbol;
		private float currentBid;
		private float currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuntity;
		private final static int DO_NOTHING = 0;
		private final static int BUY = 1;
		private final static int SELL = 2;
	}
	
	public void addStock(Stock stock){
		
		if(portfolioSize < MAX_PORTFOLIO_SIZE)
		{
			stocks[portfolioSize] = stock;
			portfolioSize++;
		}
	}
	
	public Stock[] getStocks(){
		
		return stocks;
	}
	
	public String getHtmlPortfolio(){
		
		String getHtmlPortfolio = " <h1> portfolio title: </h1>";
		
		for(i = 0 ;i < portfolioSize; i++)
		{
			getHtmlPortfolio += stocks[i].getHtmlDescription() + "<br>";
		}
		return getHtmlPortfolio;
	}
	
}
