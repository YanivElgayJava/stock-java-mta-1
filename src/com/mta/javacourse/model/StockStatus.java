package com.mta.javacourse.model;

import com.mta.javacourse.model.Portfolio.ALGO_RECOMMENDATION;


/**
 * This class represents the StockStatus which inherits Stock class features since StockStatus is a type of Stock.
 * @author Yaniv Elgay
 * @since 2014
 * date 31/12/2014
 */

public class StockStatus extends Stock {

	private ALGO_RECOMMENDATION recommendation;
	protected int stockQuantity;

	/**
	 * constructor that initialize all members and inherits features from stock class with super().
	 * @
	 */
	public StockStatus (){

		super();
		recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
		stockQuantity = 0;
	}

	/**
	 * copy constructor input values for members also used super(stockStatus) for inherited features from stock class. 
	 * @param stockStatus
	 */
	public StockStatus (StockStatus stockStatus){

		super(stockStatus);
		recommendation = stockStatus.getRecommendation();
		stockQuantity = stockStatus.getStockQuantity();
	}

	//getters:

	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	//setters:

	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

}
