package com.mta.javacourse.model;

import java.util.Date;

/**
 * An instance of this class represents a Stock with all of variables.
 * @author Yaniv Elgay
 * @since 2014
 * date 11/12/2014
 */

public class Stock {

	private String symbol;
	private float Ask;
	private float Bid;
	private java.util.Date data;

	/**
	 * copy constructor
	 * @param stocks 
	 */

	public Stock(Stock stock){
		this(stock.getSymbol(),stock.getAsk(),stock.getBid(),stock.getData());
	}

	/**
	 * constructor
	 * @param stocks 
	 */

	public Stock(String stockSymbol1, float ask1, float bid1, Date date) {
		if(stockSymbol1 != null)
		{
			setSymbol(stockSymbol1);
			setAsk(ask1);
			setBid(bid1);
			setData(date);
		}
	}

	//getters:

	public String getSymbol() {
		return symbol;
	}

	public float getAsk() {
		return Ask;
	}

	public float getBid() {
		return Bid;
	}

	public java.util.Date getData() {
		return data;
	}

	//setters:

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setAsk(float ask) {
		Ask = ask;
	}

	public void setBid(float bid) {
		Bid = bid;
	}

	public void setData(java.util.Date data) {
		this.data = data;
	} 

	/**
	 * method that return a string with stock information.
	 * @return String with stock description
	 * 
	 */

	public String getHtmlDescription(){ 
		String stockHtmlDetailsString = " <b> Stock symbol </b> : " +getSymbol()+  "<b> Ask </b> :" +getAsk()+ " <b> Bid </b> : " +getBid()+ "<b> date </b> :" +data.getDate()+ "/" +data.getMonth()+ "/" +data.getYear();
		return stockHtmlDetailsString;
	}

}
