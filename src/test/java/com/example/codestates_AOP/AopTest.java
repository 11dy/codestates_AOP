package com.example.codestates_AOP;// test/java/start.aop/AopTest.java

import com.example.codestates_AOP.order.aop.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.codestates_AOP.order.OrderRepository;
import com.example.codestates_AOP.order.OrderService;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Slf4j
@SpringBootTest
//@Import(Aspect1.class) // 스프링 빈 추가
//@Import(Aspect2.class) // 스프링 빈 추가
//@Import(Aspect3.class)
//@Import(Aspect4.class)
@Import({Aspect5.LogAspect.class, Aspect5.TxAspect.class})
public class AopTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Test
    void exception() {
        assertThatThrownBy(() -> orderService.orderItem("ex"))
                .isInstanceOf(IllegalStateException.class);
    }
}