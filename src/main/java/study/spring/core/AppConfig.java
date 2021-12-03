package study.spring.core;

import study.spring.core.discount.FixDiscountPolicy;
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
public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }
    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

    

}
