package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    //현재코드는 인터페이스를 의존하지만, 실제 할당은 구현체가 하고있다. 결론적으로 추상과 구현에 모두 의존하고있으며, 이는 DIP에 위반 됨.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //Appconfig에서 의존설정을 해주기 위해 리팩토링
    private final MemberRepository memberRepository;

    //생성자를 통해서 구현체를 선택할수 있게 구현한다.
    @Autowired//ac.getBean(MemberRepository.class)
    //@Autowired는 @Component로 빈이 생성된 객체들을 자동으로 의존주입해준다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
