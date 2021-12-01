package study.spring.core.member;

public class MemberServiceImpl implements MemberService {

    // 구현체가 없으면 npe(null pointer exception)이 뜨기 때문에 구현객체를 선택해야 한다.
    // 다형성 이용
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
