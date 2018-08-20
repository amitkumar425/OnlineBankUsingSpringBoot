package com.cg.springdemo.springbootstarter.exceptions;

public class InvalidAccountOrAmountException extends Exception {
	
	public  InvalidAccountOrAmountException(String message) {
		super(message);
	}
}
