package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//설정 정보라는걸 명시하는 @Configuration
@Configuration
public class AppConfig {
            //기존의 코드에서는 impl 에서 인터페이스를 상속받아 사용했다. => 결합성이 크다.
            //이제는 AppConfig에서 모든 환경을 맞춰준다.
    //@Bean => Spring Container에 싱글톤으로 등록이 된다.
    @Bean
    public MemberService memberService(){
        //생성자를 통해 구현체를 정해주기
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
