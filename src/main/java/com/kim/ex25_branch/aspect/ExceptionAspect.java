package com.kim.ex25_branch.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ExceptionAspect {

	@AfterThrowing(pointcut = "execution(* com.kim.ex25_branch.service.*.*(..))", throwing = "excep")
	public void logError(JoinPoint jp, Exception excep) {
		log.info("##### 메서드중 오류 Before @AfterThrowing \n : {}, \n===== 오류상황 ===== \n : {} \n##### ERROR END #####", jp.getSignature(), excep.getMessage());
	}

}