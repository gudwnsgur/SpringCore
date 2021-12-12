package study.spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring.core.discount.DiscountPolicy;
import study.spring.core.discount.FixDiscountPolicy;
import study.spring.core.discount.RateDiscountPolicy;
import study.spring.core.member.MemberRepository;
import study.spring.core.member.MemberService;
import study.spring.core.member.MemberServiceImpl;
import study.spring.core.member.MemoryMemberRepository;
import study.spring.core.order.OrderService;
import study.spring.core.order.OrderServiceImpl;

/**
 * 실제 동작에 필요한 구현 객체 생성
 * 생성한 객체 인스턴스의 레퍼런스를 생성자를 통해 주입(연결)해준다.
 * 객체의 생성과 연결을 AppConfig가 담당하면서 DIP 해결
 */

@Configuration
public class AppConfig {
    /**
     * 각 Bean들이 Spring 컨테이너에 등록된다.
     */
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
