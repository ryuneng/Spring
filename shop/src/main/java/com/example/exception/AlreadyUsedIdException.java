package com.example.exception; // 20240306 Day11

public class AlreadyUsedIdException extends ShopException {

	private static final long serialVersionUID = -1246580305351896433L;

	public AlreadyUsedIdException(String message) {
		super(message);
	}
}
