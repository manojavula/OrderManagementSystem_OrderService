package com.manojavula.orderservice;

public class OrderNotFoundException extends RuntimeException {

	public OrderNotFoundException(String message) {
		super(message);
	}
}
