package com.mta.javacourse.service;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;

/**
 * An instance of this class represents the portfoilio service.
 * @author Yaniv Elgay
 * @since 2014
 * date 11/12/2014
 */

public class PortfolioService {

	/**
	 * Return an update portfolio.
	 * @param put values to the stocks variables by using stock class 
	 * * @return myportfolio with an array of stocks.
	 */

	public Portfolio getPortfolio(){

		Portfolio myPortfolio = new Portfolio();
		java.util.Date date = new java.util.Date();

		Stock stock1 = new Stock("PIH",12.4f,13.1f,date);
		Stock stock2 = new Stock("AAL",5.5f,5.78f,date);
		Stock stock3 = new Stock("CAAS",31.5f,31.2f,date);

		date.setDate(15);
		date.setMonth(11);
		date.setYear(2014);

		myPortfolio.addStock(stock1);
		myPortfolio.addStock(stock2);
		myPortfolio.addStock(stock3);

		myPortfolio.setTitle(" <h1> Portfolio 1# </h1> ");

		return myPortfolio;
	}

}
