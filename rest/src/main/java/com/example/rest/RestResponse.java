package com.example.rest;  // 20240327 Day26

import java.util.List;

import org.springframework.http.HttpStatus;

public class RestResponse<T> {
	
	private HttpStatus status;
	private String message;
	private List<T> items;
	
	private RestResponse(HttpStatus status, String message, List<T> items) {
		this.status = status;
		this.message = message;
		this.items = items;
	}
	
	// getStatus() 반환값 숫자로 바꾸기 위해 @Getter 없애고 직접 만듦
	public String getMessage() {
		return message;
	}
	
	public List<T> getItems() {
		return items;
	}
	
	public int getStatus() {
		return status.value();
	}
	
	public static <T> RestResponse<T> getResponse(HttpStatus status, String message, List<T> items) {
		return new RestResponse<T>(status, message, items);
	}
	
	public static <T> RestResponse<T> getResponse(List<T> items) { // 조회결과 여러 건
		return getResponse(HttpStatus.OK, HttpStatus.OK.toString(), items);
	}
	
	public static <T> RestResponse<T> getResponse(T item) {        // 조회결과 1건
		return getResponse(HttpStatus.OK, HttpStatus.OK.toString(), List.of(item));
	}
	
	public static RestResponse<Void> getResponse(String message) {
		return getResponse(HttpStatus.OK, message, List.of());
	}
	
	public static RestResponse<Void> getErrorResponse(HttpStatus status, String message) {
		return getResponse(status, message, List.of());
	}
}
