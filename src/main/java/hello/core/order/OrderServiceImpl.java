package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    /***************************************************************************************/
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 인터페이스에서 구현체만 바꿔주면 된다.  => DIP를 준수하는거 같지만 구현 클래스에도 의존하고있는 형태가되어 위반된다.
    //DIP를 위반하는순간 OrderServiceImpl도 변경해야한다.(지금처럼)  => OCP 위반된다.
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /***************************************************************************************/
    //final 키워드의 경우도 생성자 주입의 이점중 하나이다. 나머지는 생성자 호출시점 뒤이기 때문에 final을 사용할 수 없음.

//    @Autowired
    private final MemberRepository memberRepository;
//    @Autowired
    private final DiscountPolicy discountPolicy;

//    //setter주입
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
//    @Autowired  //생성자 주입

//     밑의 코드를 롬복의 @RequiredArgsConstructor로 대체가능
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;   //AppConfig(외부)생성자를 통해서 주입받는다.
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
//        OrderService에서는 할인정책(discountPrice)에 대한 정보를 몰라도 된다. 밑에 discountPolicy에서 처리해주니까 => DIP(단일체계원칙)원칙을 잘지켰다.
        Member member = memberRepository.findById(memberId);
//        member 와 itemprice를 넘겨서 등급을 판별하여 등급에 맞게 discount
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
