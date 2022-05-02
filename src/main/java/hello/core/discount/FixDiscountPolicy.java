package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FixDiscountPolicy implements DiscountPolicy {

    public FixDiscountPolicy() {
        log.debug("FixDiscountPolicy 호출");
    }

    private int discountFixAmount = 1000; //1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
