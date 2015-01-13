package com.mta.javacourse.exception;

/**
 * An Exception that alerts when a stock doesn't exists.
 * @author Yaniv Elgay
 * @since 2014
 * date 13/01/2015
 */

public class StockNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public StockNotExistException (String symbol) {
		super("Stock " + symbol + " doesn't exists!");
	}
}
