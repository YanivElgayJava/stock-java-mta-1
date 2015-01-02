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

	protected String stockSymbol;
	protected float ask;
	protected float bid;
	protected Date date;

	/**
	 * constructor initialize members.
	 * @param
	 */

	public Stock(){

		stockSymbol = "";
		ask = 0;
		bid = 0;
		date = new Date();
	}

	/**
	 * copy constructor which input values to members.
	 * @param stocks 
	 */

	public Stock(Stock stock ) {
		setStockSymbol(stock.getStockSymbol());
		setAsk(stock.getAsk());
		setBid(stock.getBid());
		date = new Date();
		setDate(new Date(stock.date.getTime()));
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
