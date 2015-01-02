package com.mta.javacourse.model;

import java.util.Date;

/**
 * An instance of this class represents a portfolio which include stocks.
 * @author Yaniv Elgay
 * @since 2014
 * date 22/12/2014
 */

public class Portfolio {

	public static enum ALGO_RECOMMENDATION{DO_NOTHING,BUY,SELL};
	private final static int MAX_PORTFOLIO_SIZE = 5;
	public String title;
	private StockStatus [] stockStatus;
	private int portfolioSize;
	private int i;
	private float balance;
	private int stockStatusSize;

	/**
	 * constructor
	 * @param portfolio instances
	 */

	public Portfolio(){
		stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		stockStatusSize = 0;
		balance = 0;
		portfolioSize = 0;
		title = "";
		i = 0;
	}

	/**
	 * copy constructor
	 * @param portfolio
	 */

	public Portfolio(Portfolio portfolio){
		this();
		this.title = portfolio.title;
		this.portfolioSize = portfolio.portfolioSize;
		this.balance = portfolio.balance;
		this.stockStatusSize = portfolio.stockStatusSize;

		for(int i = 0; i < portfolio.portfolioSize ; i++){
			stockStatus[i] = new StockStatus(portfolio.stockStatus[i]);
		}
	}

	//getters:

	public Stock[] getStocks(){

		return stockStatus;
	}

	public float getBalance(){

		return balance;
	}

	//setter:

	public void setBalance(float balance){

		this.balance = balance;
	}

	/**
	 * new method which update balance
	 * @param amount
	 */

	public void updateBalance(float amount){

		balance = balance + amount;
	}

	public float getStocksValue()
	{
		float stocksValue = 0;
		int i = 0;
		while(stockStatus[i] != null)
		{
			stocksValue += stockStatus[i].bid*stockStatus[i].stockQuantity;
			i++;
		}
		return stocksValue;
	}

	public float getTotalValue()
	{
		return getBalance() + getStocksValue();
	}


	/**adding a new stock to the portfolio with limitation of maximum size(constant) and also update for each stock its status.
	 *@param stock
	 *
	 */

	public void addStock(Stock stock){

		i = 0;
		while(stockStatus[i]!=null)
		{
			if(stock.getStockSymbol().equals(stockStatus[i].getStockSymbol()))
			{
				System.out.println(" You have already this stock "+stock.getStockSymbol());
				return;
			}
			i++;
		}
		if(portfolioSize >= MAX_PORTFOLIO_SIZE)
		{
			System.out.println(" Can't add new stock, portfolio can have only "+MAX_PORTFOLIO_SIZE+" stocks");
		}
		else
		{
			StockStatus stockStatus = new StockStatus();
			stockStatus.ask = stock.getAsk();
			stockStatus.bid = stock.getBid();
			stockStatus.stockSymbol = stock.getStockSymbol();
			stockStatus.stockQuantity = 0;
			stockStatus.date = new Date(stock.getDate().getTime());
			stockStatus.setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);

			this.stockStatus[stockStatusSize] = stockStatus;
			stockStatusSize++;
			portfolioSize++;

		}
	}

	/**
	 * Remove stock from stocks array.
	 * @param stockSymbol - remove the stock from stocks array.
	 */

	public boolean removeStock(String stockSymbol){

		int i = 0;
		boolean isFound = false;
		boolean isSold = false;
		for (; i < portfolioSize; i++)
		{
			if (stockStatus[i].stockSymbol.equals(stockSymbol))
			{
				isSold = sellStock(stockSymbol, -1);
				isFound = true;
				break; 
			}
		}
		if (isFound == false) {
			System.out.println(stockSymbol+" hasn't been found");
			return false;
		}
		else{
			if(isSold==true){

				for (; i < portfolioSize; i++)
				{

					if (stockStatus[i+1]==null)
					{
						portfolioSize--;
						stockStatusSize--;
						break;
					}
					else
					{
						stockStatus[i] = stockStatus[i+1];
						stockStatus[i] = stockStatus[i + 1];
					}
				}
			}
			else 
			{
				System.out.println(stockSymbol+" hasn't been sold");
				return false;
			}
		}
		return isFound;
	}

	/**
	 * this method buyStock is used to purchase stocks, it updates the balance of the balance account.
	 * @param symbol
	 * @param quantity
	 * @return boolean success/fail
	 */

	public boolean buyStock (String symbol,int quantity){

		boolean Symbolfound = false;
		int amount;
		int i = 0;

		for (; i < portfolioSize; i++) {
			if (stockStatus[i].getStockSymbol().equals(symbol)) {
				Symbolfound = true;
				break;
			}
		}
		if(Symbolfound == true)
		{
			if (quantity == -1) {
				amount = (int) Math.floor((balance/stockStatus[i].ask));
				balance = balance - amount*stockStatus[i].ask;
				return true;
			}
			else{
				if(stockStatus[i].ask*quantity > balance)
				{
					System.out.println("Not enough balance to complete purchase");
					return false;
				}
				stockStatus[i].stockQuantity=stockStatus[i].stockQuantity+quantity;
				balance = balance - stockStatus[i].ask*quantity;
				return true;
			}
		}
		else
		{
			System.out.println(symbol+" purchase has failed");
			return false;
		}
	}

	/**
	 * this method sellStock is used to sell stocks,but not remove stocks.
	 * @param symbol
	 * @param quantity
	 * @return boolean success/fail
	 */

	public boolean sellStock(String symbol,int quantity){

		boolean symbolFound = false;
		int i = 0;

		for(; i < portfolioSize; i++)
		{
			if(stockStatus[i].getStockSymbol().equals(symbol))
			{
				symbolFound = true;
				break;
			}	

		}
		if(symbolFound==true){

			if (quantity == -1) {
				balance = balance + stockStatus[i].stockQuantity*stockStatus[i].bid;
				stockStatus[i].stockQuantity = 0;
				return true;
			}
			if (quantity == MAX_PORTFOLIO_SIZE || quantity > MAX_PORTFOLIO_SIZE || quantity < 0) {
				System.out.println(symbol+" hasn't been sold - Not enough stocks to sell");
				return false;
			}
			else{
				stockStatus[i].stockQuantity = stockStatus[i].stockQuantity - quantity;
				balance = balance + stockStatus[i].bid*quantity;
				return true;
			}
		}
		else
		{
			System.out.println(symbol+" hasn't been sold");
			return false;
		}

	}


	/**
	 * method that return a string with portfolio description and update the totalValue and stockValue and Balance.
	 * @return String with stock description	
	 */

	public String getHtmlPortfolio(){

		String getHtmlPortfolio = title;

		for(i = 0 ;i < portfolioSize; i++)
		{
			getHtmlPortfolio += stockStatus[i].getHtmlDescription()+ " , <b>quantity</b>: " +stockStatus[i].getStockQuantity()+ "<br><br>";
		}
		getHtmlPortfolio += "<br>"+"Total Portfolio Value: "+getTotalValue()
				+"$, "+"<br>"+"Total Stocks value:" + getStocksValue()
				+"$, "+"<br>"+"Balance: "+getBalance()+"$"+"<br>";

		return getHtmlPortfolio;
	}

}

