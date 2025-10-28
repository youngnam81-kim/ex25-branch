package com.kim.ex25_branch.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

	@Before("execution(* com.kim.ex25_branch.service.*.*(..))")
	public void logbefore(JoinPoint jp) {
		log.info("\n##### 메소드 실행전 Before ##### \n : {} \n##### Before #####", jp.getSignature());
	}

//	@AfterThrowing(pointcut = "execution(* com.kim.ex25_branch.service.*.*(..))", throwing = "excep")
//	public void logError(JoinPoint jp, Exception excep) {
//		log.info("##### 메서드\n 예외: {}, \n오류: {} #####end#####", jp.getSignature(), excep.getMessage());
//	}

	@AfterReturning(pointcut = "execution(* com.kim.ex25_branch.service.*.*(..))", returning = "result")
	public void logAfter(JoinPoint jp, Object result) {
		log.info("\n##### 메서드 실행성공 AfterReturning ##### \n : {}, \n결과:\n {} \n##### AfterReturning #####", jp.getSignature(), result);
	}
	
	// 어드바이스는 메서드 실행 결과와 상관없이 (성공이든 예외 발생이든) 항상 실행되며, 
	// 반환값에 접근할 수 없습니다. 따라서 단순히 메서드 실행이 완료되었다는 정보만 로깅하게 됩니다.
	@After("execution(* com.kim.ex25_branch.service.*.*(..))")
	public void logAfter1(JoinPoint jp) {
	    log.info("\n##### 메서드 After #####\n : {} \n##### After #####", jp.getSignature());
	}

}