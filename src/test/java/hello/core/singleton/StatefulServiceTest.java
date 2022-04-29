package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB : B사용자 20000원 주문
        int userBPrice = statefulService2.order("userA", 20000);

        //ThreadA:사용자A 주문 금액 조회
        //A사용자가 주문요청을 하고 금액을 조회하는 사이에 B사용자가 주문요청 하게될 경우 싱글톤으로 같은 객체사용
        //=>원하는 A사용자의 주문금액이아닌 B사용자의 주문금액이 조회가된다.
//        int price = statefulService1.getPrice();
        //여기서 싱글톤의 문제점이 발생한다. 하나의 객체로 사용하기때문에 여러요청이 한번에 들어올때
        //값이 Setter를 타고 계속해서 초기화.
        System.out.println("price = " + userAPrice);
        System.out.println("price = " + userBPrice);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }



}