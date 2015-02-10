package com.mta.javacourse.model;

import java.util.ArrayList;
import java.util.List;

import com.mta.javacourse.exception.BalanceException;
import com.mta.javacourse.exception.IllegalQuantityException;
import com.mta.javacourse.exception.PortfolioFullException;
import com.mta.javacourse.exception.StockAlreadyExistsException;
import com.mta.javacourse.exception.StockNotExistException;
import com.mta.javacourse.service.PortfolioService;

/**
 * An instance of this class represents a portfolio which include stocks.
 * @author Yaniv Elgay
 * @since 2014
 * date 22/12/2014
 */

public class Portfolio {

	public static enum ALGO_RECOMMENDATION {DO_NOTHING, BUY, SELL};
	public static final int MAX_PORTFOLIO_SIZE = 5;
	public int portfolioSize;
	private int stockStatusSize;
	public String title;
	private List<StockStatus> stockStatus;
	private float balance;

	/**
	 * constructor
	 * @param portfolio instances
	 */

	public Portfolio()
	{
		portfolioSize = 0;
		stockStatusSize = 0;
		title = "PortfolioDefault";
		balance = 0;
		stockStatus = new ArrayList<StockStatus>(MAX_PORTFOLIO_SIZE);
	}

	/**
	 * copy constructor
	 * @param portfolio
	 */

	public Portfolio(Portfolio portfolio)
	{
		this();
		title = portfolio.title;
		portfolioSize = portfolio.portfolioSize;
		stockStatusSize = portfolio.stockStatusSize;
		stockStatus.addAll(portfolio.stockStatus);
		balance = portfolio.balance;
	}


	public Portfolio(List <StockStatus> stockStatus)
	{

		this();

		for (int i = 0; i < stockStatus.size(); i++) {
			this.stockStatus.add(stockStatus.get(i));
		}
	}

	/**
	 * Prints a stockStatus to the console.
	 * @param stockStatus
	 */

	public void printStockStatus (StockStatus stockStatus)
	{
		if(stockStatus==null)
		{
			System.out.println("Empty cell");
			return;
		}
		System.out.println("symbol: "+stockStatus.stockSymbol+
				"\n"+"ask: "+stockStatus.ask+
				"\n"+"bid: "+stockStatus.bid+
				"\n"+"date: "+stockStatus.date+
				"\n"+"stockQuantity: "+stockStatus.stockQuantity+
				"\n"+"recommendation: "+stockStatus.getRecommendation());
	}

	/**
	 * new method which update balance
	 * @param amount
	 */

	public void updateBalance(float amount)
	{
		balance = balance + amount;
	}


	public float getStocksValue()
	{
		float res = 0;		
		for (int i=0; i < stockStatus.size() ; i++){
			res += stockStatus.get(i).getStockQuantity() * stockStatus.get(i).getBid();
		}

		return res;

	}

	public float getBalance()
	{
		return balance;
	}

	public float getTotalValue()
	{
		return getBalance()+getStocksValue();
	}

	/**
	 * Remove stock from stocks array.
	 * @param stockSymbol - remove the stock from stocks array.
	 */
	public void removeStock(String symbol) throws StockNotExistException, IllegalQuantityException 
	{	
		int index = get_index_of_symbol(symbol);

		if(index ==-1){
			throw new StockNotExistException(symbol);
		}

		sellStock(symbol, -1);

		while (index < stockStatus.size())
		{
			stockStatus.remove(index);
			index++;
		}
	}

	/**
	 * get symbol and find the place in the array - the index of symbol 
	 * @param symbol
	 * @return
	 */
	private int get_index_of_symbol(String symbol)
	{
		for(int i = 0; i < stockStatus.size(); i++)
		{
			if(stockStatus.get(i).getStockSymbol().toLowerCase().equals(symbol.toLowerCase()))
			{
				return i;
			}
		}
		return -1; 
	}


	/**
	 * this method buyStock is used to purchase stocks, it updates the balance of the balance account.
	 * @param symbol
	 * @param quantity
	 * @return boolean success/fail
	 */
	public void buyStock (String symbol,int quantity) throws BalanceException, StockNotExistException{

		boolean Symbolfound=false;
		int amount;
		int i = 0;
		for (; i < stockStatus.size(); i++) {
			if (stockStatus.get(i).getStockSymbol().equals(symbol)) {
				Symbolfound=true;
				break;
			}
		}
		if(Symbolfound==true)
		{
			if (quantity==-1) 
			{
				amount=(int) Math.floor((balance/stockStatus.get(i).ask));

				if(stockStatus.get(i).ask*amount<balance)
				{
					System.out.println("Not enough balance to complete purchase");
					throw new BalanceException();
				}
				else
				{
					balance=balance - amount*stockStatus.get(i).ask;
					stockStatus.get(i).stockQuantity=stockStatus.get(i).stockQuantity+amount;
				}

			}
			else{
				if(stockStatus.get(i).ask*quantity>balance)
				{
					System.out.println("Not enough balance to complete purchase");
					throw new BalanceException();
				}
				stockStatus.get(i).stockQuantity=stockStatus.get(i).getStockQuantity()+quantity;
				balance=balance - stockStatus.get(i).ask*quantity;
			}
		}
		else
		{
			System.out.println(symbol+" was not found");
			throw new StockNotExistException(symbol);
		}
	}

	/**
	 * this method sellStock is used to sell stocks,but not remove stocks.
	 * @param symbol
	 * @param quantity
	 * @return boolean success/fail
	 */
	public void sellStock (String symbol, int quantity) throws StockNotExistException{
		boolean Symbolfound=false;
		int i = 0;
		for (; i < stockStatus.size(); i++) {
			if (stockStatus.get(i).getStockSymbol().equals(symbol)) {
				Symbolfound=true;
				break;
			}
		}
		if(Symbolfound==true)
		{
			if (quantity==-1) {
				balance=balance+stockStatus.get(i).stockQuantity*stockStatus.get(i).bid;
				stockStatus.get(i).stockQuantity=0;
			}
			else if (quantity>stockStatus.get(i).stockQuantity||quantity<0) {
				System.out.println(symbol+" hasn't been sold - Not enough stocks to sell"+"\n"+"you have only "+ stockStatus.get(i).stockQuantity);
			}
			else{
				stockStatus.get(i).stockQuantity=stockStatus.get(i).stockQuantity-quantity;
				balance=balance + stockStatus.get(i).bid*quantity;
			}
		}
		else
		{
			System.out.println(symbol+" hasn't been sold");
			throw new StockNotExistException(symbol);
		}



	}

	/**adding a new stock to the portfolio with limitation of maximum size(constant) and also update for each stock its status.
	 *@param stock
	 *
	 */
	public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException {

		if(stockStatus.size()==MAX_PORTFOLIO_SIZE)
		{
			System.out.println("Cant add new stock, portfolio can have only "+ MAX_PORTFOLIO_SIZE +" stocks");
			throw new PortfolioFullException();
		}

		for (int i = 0; i < stockStatus.size(); i++) {

			if (stockStatus.get(i).getStockSymbol().equals(stock.getStockSymbol())) {
				System.out.println(stockStatus.get(i).getStockSymbol() + " Already exists in the portfolio");

				throw new StockAlreadyExistsException(stock.getStockSymbol());
			}
		}

		stockStatus.add(new StockStatus(stock));		
	}

	public StockStatus[] getStocks() {
		StockStatus[] res = new StockStatus[stockStatus.size()];
		res =  stockStatus.toArray(res);
		return res;
	}


	/**
	 * method that return a string with portfolio description and update the totalValue and stockValue and Balance.
	 * @return String with stock description	
	 */
	public String getHtmlString(){

		String HtmlString= new String("<h1><u>"+title+"</u></h1>"); 
		for (int i = 0; i < portfolioSize; i++) 
		{ 
			if(stockStatus.get(i)!=null)
			{
				HtmlString=HtmlString+(stockStatus.get(i).getHtmlDescription()+"<br>");
			}
		}
		HtmlString+="<br>"+"Total Portfolio Value: "+getTotalValue()
				+"$, "+"<br>"+"Total Stocks value:" + getStocksValue()
				+"$, "+"<br>"+"Balance: "+getBalance()+"$"+"<br>";

		return HtmlString;
	}

	public StockStatus findBySymbol(String symbol) throws BalanceException,StockNotExistException, StockAlreadyExistsException, StockNotExistException, PortfolioFullException {

		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();

		System.out.println("method findBySymbol(String symbol)");
		System.out.println("portfolio.stockStatusSize in findBySymbol is:" + portfolio.stockStatusSize);
		for (int i = 0; i < portfolio.stockStatusSize; i++) {
			if (symbol.equals(portfolio.stockStatus.get(i).stockSymbol)) {
				System.out.println("worked");
				return portfolio.stockStatus.get(i);
			}
		}
		System.out.println("error");
		return null;


	}

	public void printPortfolio(Portfolio portfolio)
	{
		System.out.println("method printPortfolio");
		System.out.println("portfolioSize in portfolioPrint is:" + portfolio.portfolioSize);
		for (int i = 0; i < portfolio.portfolioSize; i++) {
			System.out.println(portfolio.stockStatus.get(i).getHtmlDescription());
		}
	}

	public void printStockStatusList (List<StockStatus> stockStatuses)
	{
		System.out.println("method printStockStatusList");
		System.out.println("stockStatuses.size() in printStockStatusList is:" + stockStatuses.size());
		for (int i = 0; i < stockStatuses.size(); i++) {
			System.out.println(stockStatuses.get(i).stockSymbol);
		}
	}

	public List<StockStatus> getStocksStatus() {
		return stockStatus;
	}
	public void setStocksStatus(List<StockStatus> stocksStatus) {
		this.stockStatus = stocksStatus;
	}

	public int getPortfolioSize() {
		return stockStatus.size();
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
}
