package com.mta.javacourse.model;

import java.util.Date;

/**
 * An instance of this class represents a portfolio which include stocks.
 * @author Yaniv Elgay
 * @since 2014
 * date 11/12/2014
 */

public class Portfolio {

	private final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private Stock [] stocks = new Stock[MAX_PORTFOLIO_SIZE];
	private StockStatus [] stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	private int portfolioSize;
	private int i;

	//getters:

	public String getTitle() {
		return title;
	}

	public int getPortfolioSize(){
		return portfolioSize = 0;
	}

	public StockStatus[] getStocksStatus() {
		return stockStatus;
	}

	public Stock[] getStocks(){

		return stocks;
	}

	//setters:

	public void setTitle(String title) {
		this.title = title;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.stockStatus = stocksStatus;
	}

	/**
	 * constructor
	 * @param portfolio instances
	 */

	public Portfolio(){
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		setStocksStatus(new StockStatus[MAX_PORTFOLIO_SIZE]);
	}

	/**
	 * copy constructor
	 * @param portfolio
	 */

	public Portfolio(Portfolio portfolio){
		this();
		this.title = portfolio.getTitle();
		this.portfolioSize = portfolio.portfolioSize;

		for(int i = 0; i < portfolio.portfolioSize ; i++){
			stocks[i] = new Stock(portfolio.getStocks()[i]);
			stockStatus[i] = new StockStatus(portfolio.getStocksStatus()[i]);
		}
	}

	/**
	 * constructor
	 * @param portfolio
	 */

	public Portfolio(String title1,Stock[] stocks1,StockStatus[] stockStatus1,int portfolioSize1){
		setTitle(title1);
		setStocks(stocks1);
		setStocksStatus(stockStatus1);
		setPortfolioSize(portfolioSize1);
	}

	/**
	 * An instance of this inner class represents stock status.
	 * @author Yaniv Elgay
	 * @since 2014
	 * date 11/12/2014
	 */
	public class StockStatus{

		private final static int DO_NOTHING = 0;
		private final static int BUY = 1;
		private final static int SELL = 2;

		private String symbol;
		private float currentBid;
		private float currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuantity;

		/** copy constructor
		 * @param StockStatus
		 */

		public StockStatus(){

		}

		/**
		 * constructor
		 * @param StockStatus
		 */

		public StockStatus(StockStatus stockStatus){
			if(this.symbol != null)
			{
				this.symbol = stockStatus.symbol;
				this.currentAsk = stockStatus.currentAsk;
				this.currentAsk = stockStatus.currentBid;
				this.date = stockStatus.date;
				this.recommendation = stockStatus.recommendation;
				this.stockQuantity = stockStatus.stockQuantity;
			}
		}

		//getters:

		public String getSymbol() {
			return symbol;
		}

		public float getCurrentBid() {
			return currentBid;
		}

		public float getCurrentAsk() {
			return currentAsk;
		}

		public Date getDate() {
			return date;
		}

		public int getRecommendation() {
			return recommendation;
		}

		public int getStockQuantity() {
			return stockQuantity;
		}

		//setters:

		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}

		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public void setRecommendation(int recommendation) {
			this.recommendation = recommendation;
		}

		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}

	}

	/**
	 * adding a new stock to the portfolio with limitation of maximum size(constant).
	 * @param stock
	 */

	public void addStock(Stock stock){

		if(portfolioSize < MAX_PORTFOLIO_SIZE)
		{
			stocks[portfolioSize] = stock;
			portfolioSize++;
		}
	}

	/**
	 * Remove stock from stocks array.
	 * @param stock - remove the stock from stocks array.
	 */

	public void removeStock(int index){
		if(index == portfolioSize)
			this.portfolioSize--;
		else 
		{
			this.portfolioSize--;
			for(int i = index; i <= portfolioSize-1; i++)
			{
				this.stocks[i] = this.stocks[i+1];
			}
		}
	}

	/**
	 * method that return a string with portfolio description.
	 * @return String with stock description	
	 */

	public String getHtmlPortfolio(){

		String getHtmlPortfolio = getTitle();

		for(i = 0 ;i < portfolioSize; i++)
		{
			getHtmlPortfolio += stocks[i].getHtmlDescription() + "<br>";
		}
		return getHtmlPortfolio;
	}

}

