package com.example.codestates_AOP.order.aop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class Aspect1 {
    @Around("execution(* com.example.codestates_AOP.order..*(..))") // 어노테이션의 값은 포이트컷이 된다. start.aop.order 패키지와 하위 패키지 ..를 지정한 AspectJ 포인트컷 표현식
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable { // logging은 어드바이스가 된다.
        log.info("log -> {}", joinPoint.getSignature()); // log -> 정보 메소드 실행 정보를 나타냄
        return joinPoint.proceed();
    }
}
//OrderService와 OrderRepository의 모든 메서드는 AOP 적용 대상이 됩니다.
// 프록시가 적용되면서 AopUtils.isAopProxy(…) 반환 값은 true가 됩니다.