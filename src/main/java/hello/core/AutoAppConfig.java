package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import jdk.jfr.StackTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//기존에 수동적으로 Bean등록을 했다면, @ComponentScan을 통한 자동 Bean등록 방법
@Configuration
//@Component가 붙은 Bean정보들을 모두 스캔해서 컨테이너에 등록해줌
@ComponentScan(
        //default값은 @ComponentScan어노테이션이 붙은 클래스의 패키지 하위 목록 => hello.core
        //basePacakge하위목록 스캔 => hello.core.member
//        basePackages = "hello.core.member",
        //해당 클래스의 패키지 하위목록 스캔  => hello.core
//        basePackageClasses = AutoAppConfig.class,
        //스캔 중에 필터걸어서 원하는 항목만 스캔
        //AppConfig에 있는 @Configuration과 충돌 방지
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
/*
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
*/
}