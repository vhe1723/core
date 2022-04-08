package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
//        OrderService에서는 할인정책(discountPrice)에 대한 정보를 몰라도 된다. 밑에 discountPolicy에서 처리해주니까 => DIP(단일체계원칙)원칙을 잘지켰다.
        Member member = memberRepository.findById(memberId);
//        member 와 itemprice를 넘겨서 등급을 판별하여 등급에 맞게 discount
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
