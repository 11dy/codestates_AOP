package com.example.codestates_AOP.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class Aspect3 { // 어드바이스 추가 예제. 로그 출력 기능에 추가로 트랜잭션이 동작하는 것처럼 로그 남기는 기능을 추가

    @Pointcut("execution(* com.example.codestates_AOP.order..*(..))")
    private void allOrder(){}

    @Pointcut("execution(* *..*Service.*(..))") // 이름이 service로 끝나는 것을 대상
    private void allService(){}

    @Around("allOrder()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("log -> {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    @Around("allOrder() && allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            log.info("트랜잭션 시작 -> {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("트랜잭션 커밋 -> {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("트랜잭션 롤백 -> {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("리소스 릴리즈 -> {}", joinPoint.getSignature());
        }
    }
}
//OrderService에는 logging과 doTransaction이 모두 적용됩니다.
//OrderRepository에는 logging만 적용됩니다.