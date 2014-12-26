package com.mta.javacourse.service;

import java.util.Date;

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
	public Portfolio getPortfolio(){

		Portfolio myPortfolio = new Portfolio();
		Date date = new java.util.Date();

		myPortfolio.setBalance(10000);

		date.setMonth(11);
		date.setDate(15);
		date.setYear(114);

		Stock stock1 = new Stock("PIH",10,8.5f,date);
		Stock stock2 = new Stock("AAL",30,25.5f,date);
		Stock stock3 = new Stock("CAAS",20,15.5f,date);

		myPortfolio.addStock(stock1);
		myPortfolio.addStock(stock2);
		myPortfolio.addStock(stock3);

		myPortfolio.buyStock("PIH",20);
		myPortfolio.buyStock("AAL",30);
		myPortfolio.buyStock("CAAS",40);

		myPortfolio.sellStock("AAL",-1);
		myPortfolio.removeStock("CAAS");

		myPortfolio.setTitle(" <h1> Exercise 7 Portfolio </h1> ");

		return myPortfolio;
	}

}
