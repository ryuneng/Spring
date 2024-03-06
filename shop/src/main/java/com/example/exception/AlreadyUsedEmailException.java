package com.example.exception; // 20240306 Day11

public class AlreadyUsedEmailException extends ShopException {

	private static final long serialVersionUID = 6974693762699709632L;

	public AlreadyUsedEmailException(String message) {
		super(message);
	}
}
