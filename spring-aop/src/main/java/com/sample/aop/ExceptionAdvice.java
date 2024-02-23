package com.sample.aop; // 20240221 Day2

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionAdvice {

	@AfterThrowing(pointcut = "execution(* com.sample.service.*.*(..))", throwing = "ex")
	public void handleException(Exception ex) {
		System.out.println("예외가 발생했습니다. 오류 메세지: " + ex.getMessage());
	}
}
