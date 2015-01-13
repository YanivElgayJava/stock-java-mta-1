package com.mta.javacourse.exception;

/**
 * An Exception that alerts when you have stock that already exits in portfolio.
 * @author Yaniv Elgay
 * @since 2014
 * date 13/01/2015
 */

public class StockAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public StockAlreadyExistsException(String symbol) {
		super("Sorry, stock " + symbol + " already exists in portfolio!");
	}
}

