package com.kim.ex25_branch.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class PerformanceAspect {

	@Around("execution(* com.kim.ex25_branch.service.*.*(..))")
	public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis(); // 시작 시간 기록

        Object result = null;
        try {
            // 핵심 비즈니스 로직 실행
            result = joinPoint.proceed(); 
        } finally {
            long endTime = System.currentTimeMillis(); // 종료 시간 기록
            long executionTime = endTime - startTime;

            log.info("\n##### 메서드 실행 시간 ##### \n : {}ms - {}.{}() \n##### 시간 #####", 
                     executionTime,
                     joinPoint.getSignature().getDeclaringTypeName(),
                     joinPoint.getSignature().getName());
        }
        return result;

	}

}