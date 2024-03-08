package com.example.web.advice; // 20240308 Day13

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
    @ControllerAdvice
    	- 여러 컨트롤러 클래스에서 공통으로 사용되는 기능이 정의된 클래스임을 나타내는 어노테이션
    	- 컨트롤러에서 사용된 공통기능이 구현된 메서드에는 아래의 어노테이션 중 하나가 붙는다.
    	  @ExceptionHandler
    	  		- 예외처리를 담당하는 메서드임을 나타내는 어노테이션
    	  @InitBinder
    	  		- 컨트롤러로 전달되는 요청 파라미터에 대한 추가적인 작업을 수행하는 메서드임을 나타내는 어노테이션
    	  @ModelAttribute
    	  		- 
 */
@ControllerAdvice // 모든 컨트롤러에서 공통으로 구현하는 기능이 정의되어 있는 클래스
public class ExceptionHandlerAdvice {

	// 여러개의 @ExceptionHandler가 있을 경우, 더 구체적인 Exception에 매핑됨
	// 여기서는 DataAccessException에 해당하는 예외 발생 시, DataAccessException와 Exception 중 DataAccessException에 매핑됨
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandle() {
		return "error/db";
	}
	
	@ExceptionHandler(Exception.class) // Exception
	public String exceptionHandle() {
		return "error/server";
	}
}
