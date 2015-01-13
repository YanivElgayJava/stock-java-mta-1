package com.mta.javacourse.exception;

/**
 * An Exception that alerts when your portfolio reached maximum size.
 * @author Yaniv Elgay
 * @since 2014
 * date 13/01/2015
 */

public class PortfolioFullException extends Exception {

	private static final long serialVersionUID = 1L;

	public PortfolioFullException() {
		super("You had reached maximum portfolio size!");
	}
}
