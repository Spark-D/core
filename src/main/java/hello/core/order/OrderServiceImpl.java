package hello.core.order;

import hello.core.annotaion.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemberMemoryRepository();
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //    정액 -> 정률 바꾸려면 orderserviceImple 소스를 바꿔야해
    //    추상에 의존하지 않고 구체에 의존하고있다  OCP 위반, DIP 위반
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //lombok @RequiredArgsConstructor 어노테이션으로 교체
     // 생성자 위에 오토와이어드 해주면 자동 의존관계를 주입 받는다
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    // interface에만 의존하고있다!

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findByIdMember(memberId);
        int discountAmount = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountAmount);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
