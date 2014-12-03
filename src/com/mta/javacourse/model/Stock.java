package com.mta.javacourse.model;

public class Stock {
	
	private String symbol;
	private float Ask;
	private float Bid;
	private java.util.Date data;
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public float getAsk() {
		return Ask;
	}
	
	public void setAsk(float ask) {
		Ask = ask;
	}
	
	public float getBid() {
		return Bid;
	}
	
	public void setBid(float bid) {
		Bid = bid;
	}
	
	public java.util.Date getData() {
		return data;
	}
	
	public void setData(java.util.Date data) {
		this.data = data;
	} 
	
	public String getHtmlDescription(){ 
		String stockHtmlDetailsString = " <b> Stock symbol </b> : " +getSymbol()+  "<b> Ask </b> :" +getAsk()+ " <b> Bid </b> : " +getBid()+ "<b> date </b> :" +data.getDate()+ "/" +data.getMonth()+ "/" +data.getYear();
		return stockHtmlDetailsString;
	}
	
		
	
}
