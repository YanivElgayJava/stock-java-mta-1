package com.mta.javacourse.service;

import java.util.Date;

import com.mta.javacourse.exception.BalanceException;
import com.mta.javacourse.exception.PortfolioFullException;
import com.mta.javacourse.exception.StockAlreadyExistsException;
import com.mta.javacourse.exception.StockNotExistException;
import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;


/**
 * An instance of this class represents the portfoilio service.
 * @author Yaniv Elgay
 * @since 2014
 * date 22/12/2014
 */

public class PortfolioService {

	/**
	 * Return an update portfolio.
	 * @param put values to the stocks variables by using stock class 
	 * * @return myportfolio with an array of stocks.
	 */

	@SuppressWarnings("deprecation")
	public Portfolio getPortfolio() throws BalanceException,PortfolioFullException,StockAlreadyExistsException,StockNotExistException{

		Portfolio myPortfolio = new Portfolio();
		Stock stock1 = new Stock();
		Stock stock2 = new Stock();
		Stock stock3 = new Stock();
		Stock addSameAsStock3 = new Stock();

		stock1.setStockSymbol("PIH");
		stock1.setAsk(10f);
		stock1.setBid(8.5f);
		stock1.setDate(new Date(114,11,15));

		stock2.setStockSymbol("AAL");
		stock2.setAsk(30f);
		stock2.setBid(25.5f);
		stock2.setDate(new Date(114,11,15));

		stock3.setStockSymbol("CAAS");
		stock3.setAsk(20f);
		stock3.setBid(15.5f);
		stock3.setDate(new Date(114,11,15));

		addSameAsStock3.setStockSymbol("CAAS");
		addSameAsStock3.setAsk(20f);
		addSameAsStock3.setBid(15.5f);
		addSameAsStock3.setDate(new Date(114,11,15));

		myPortfolio.setBalance(10000);

		myPortfolio.addStock(stock1);
		myPortfolio.addStock(stock2);
		myPortfolio.addStock(stock3);
		myPortfolio.addStock(addSameAsStock3);

		myPortfolio.buyStock("PIH",20);
		myPortfolio.buyStock("AAL",30);
		myPortfolio.buyStock("CAAS",40);

		myPortfolio.sellStock("AAL",-1);
		myPortfolio.removeStock("CAAS");

		myPortfolio.title = " <h1> Exercise 7 Portfolio </h1> ";

		return myPortfolio;
	}

}
