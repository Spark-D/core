package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();
    
    @Test
    @DisplayName("VIP는 10%할인이 되어야 한다")
    void testDiscount() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 20000);
        //then
        assertThat(discount).isEqualTo(2000);

    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void testBasicDiscount() {
        //given
        Member member2 = new Member(2L, "memberB", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(member2, 20000);
        //then
        assertThat(discount).isEqualTo(0);
    }
            

}