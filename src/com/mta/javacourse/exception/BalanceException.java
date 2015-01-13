package com.mta.javacourse.exception;

/**
 * An Exception that alerts when your balance is negative.
 * @author Yaniv Elgay
 * @since 2014
 * date 13/01/2015
 */

public class BalanceException extends Exception {

	private static final long serialVersionUID = 1L;

	public BalanceException() {
		super("Your balance is negative, therefore you can't purchase more stocks!");
	}
}
