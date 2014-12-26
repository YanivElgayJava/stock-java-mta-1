package com.mta.javacourse.model;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * An instance of this class represents a Stock with all of variables.
 * @author Yaniv Elgay
 * @since 2014
 * date 22/12/2014
 */

public class Stock {

	private String stockSymbol;
	private float ask;
	private float bid;
	private Date date;

	/**
	 * copy constructor
	 * @param stocks 
	 */

	public Stock(Stock stock){
		this(stock.getStockSymbol(),stock.getAsk(),stock.getBid(),new Date(stock.getDate().getTime()));
	}

	/**
	 * constructor
	 * @param stocks 
	 */

	public Stock(String stockSymbol, float ask, float bid,Date date ) {
		if(stockSymbol != null)
		{
			setStockSymbol(stockSymbol);
			setAsk(ask);
			setBid(bid);
			setDate(date);
		}
		else
		{
			System.out.println("There are not stocks to copy");
		}
	}

	//getters:

	public String getStockSymbol() {
		return stockSymbol;
	}

	public float getAsk() {
		return ask;
	}

	public float getBid() {
		return bid;
	}

	public Date getDate() {
		return date;
	}

	//setters:

	public void setStockSymbol(String symbol) {
		stockSymbol = symbol;
	}

	public void setAsk(float inputAsk) {
		ask = inputAsk;
	}

	public void setBid(float inputBid) {
		bid = inputBid;
	}

	public void setDate(Date inputDate) {
		date = inputDate;
	} 

	/**
	 * method that return a string with stock information.
	 * @return String with stock description
	 * 
	 */

	public String getHtmlDescription(){ 
		String stockHtmlDetailsString = " <b> Stock symbol </b> : " +getStockSymbol()+  "<b> Ask </b> :" +getAsk()+ " <b> Bid </b> : " +getBid()+ "<b> date </b> :" +new SimpleDateFormat("dd-MM-yyyy").format(date);
		return stockHtmlDetailsString;
	}

}
