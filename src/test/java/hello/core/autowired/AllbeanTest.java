package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllbeanTest {

    @Test
    void findAllbean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = context.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> discountPolicyMap;
        private final List<DiscountPolicy> discountPolicies;

        public DiscountService(Map<String, DiscountPolicy> discountPolicyMap, List<DiscountPolicy> discountPolicies) {
            this.discountPolicyMap = discountPolicyMap;
            this.discountPolicies = discountPolicies;
            System.out.println("discountPolicyMap = " + discountPolicyMap);
            System.out.println("discountPolicies = " + discountPolicies);

        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = discountPolicyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
