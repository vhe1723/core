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
//Configuration어노테이션은 스프링 컨테이너가 관리할 수 있게 해줌
// => 스프링 컨테이너에 생성된 @Bean이 붙은 객체를 컨테이너에서 찾아 반환해준다.
@Configuration
public class AppConfig {
            //기존의 코드에서는 impl 에서 인터페이스를 상속받아 사용했다. => 결합성이 크다.
            //이제는 AppConfig에서 모든 환경을 맞춰준다.

    //빈의 실행순서
    //@Bean이 memberService -> new MemoryMemberRepository()
    //@Bean OrderService -> new MemoryMemberRepository()
    //결국 두번호출 => 싱글톤이 깨짐 =>

    //@Bean => Spring Container에 싱글톤으로 등록이 된다.
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        //생성자를 통해 구현체를 정해주기
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
