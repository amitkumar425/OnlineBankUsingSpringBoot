package com.cg.springdemo.springbootstarter.exceptions;

public class AccountNotFoundException extends Exception{

	public AccountNotFoundException(String message) {
		super(message);
	}
}
