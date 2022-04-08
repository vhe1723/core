package hello.core.member;

public class MemberServiceImpl implements MemberService {

    //현재코드는 인터페이스를 의존하지만, 실제 할당은 구현체가 하고있다. 결론적으로 추상과 구현에 모두 의존하고있으며, 이는 DIP에 위반 됨.
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
