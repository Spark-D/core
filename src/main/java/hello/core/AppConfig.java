package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberMemoryRepository;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//구성(설정)정보 담당하는 파일이다
@Configuration
//공연기획자 역할
public class AppConfig {

    //스프링컨테이너에 빈을 등록하는 행위
    @Bean
    public MemberService memberService() {
        System.out.println("call memberService");
        return new MemberServiceImpl(memberRepository()); //생성자 주입
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call memberRepository");
        return new MemberMemoryRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
