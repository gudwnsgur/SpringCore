package study.spring.core.order;

import study.spring.core.discount.DiscountPolicy;
import study.spring.core.discount.FixDiscountPolicy;
import study.spring.core.discount.RateDiscountPolicy;
import study.spring.core.member.Member;
import study.spring.core.member.MemberRepository;
import study.spring.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    /**
     * 문제점
     * 1. DIP 위반 : 추상(인터페이스) 뿐만 아니라 구체(구현) 클래스에도 의존하고 있다.
     *            DiscountPolicy 뿐 아니라 FixDiscountPolicy, RateDiscountPolicy도 의존
     * 2. OCP 위반 : 구체(구현) 클래스를 변경하는 순간 다른 클래스도 변경해야 한다. ( 운전면허증을 갱신해야 하는 상황...;)
     *            RateDiscountPolicy로 변경하는 순간 OrderServiceImpl도 변경해야 한다.
     */
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /**
     * 해결방법
     * 1. DIP : 추상(인터페이스)에만 의존하도록 의존관계를 변경하면 된다.
     *    ex)  private DiscountPolicy discountPolicy;
     *      => 인터페이스에만 의존하기 때문에 DIP를 해결할 수 있지만, 구현체가 없어 실행이 불가능.... ㅎㅎ
     *    이 문제를 해결하려면 누군가 클라이언트(OrderServiceImpl)에
     *    인터페이스(DicountPolicy)의 구현 객체를 대신 생성하고 주입해주어야 한다.
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
