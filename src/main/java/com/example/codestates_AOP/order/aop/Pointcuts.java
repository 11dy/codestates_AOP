package com.example.codestates_AOP.order.aop;
import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    //포인트컷을 공용으로 사용하기 위해 외부 클래스로 외부에 모아둠
    // 외부에서 호출할 때는 포인트컷의 접근 제한자는 퍼블릭이어야한다.
    @Pointcut("execution(* com.example.codestates_AOP.order..*(..))")
    public void allOrder(){}

    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}

    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}

}
