package hello.core.singletone;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletoneTest {

    @Test
    @DisplayName("스프링없는 순수한 DI 컨테이너")
    void pureContainer () {
        AppConfig appConfig = new AppConfig();
        //1. 호출할때마다 객체를 생성하는지 확인
        MemberService memberService1 = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        //2. 참조값이 다른 것 확인 -> 이렇게 되면 JVM 메모리에 계속해서 인스턴스를 생성함
        System.out.println("memberService1: " + memberService1);
        System.out.println("memberService2: " + memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest () {
        SingletoneService singletoneService1 = SingletoneService.getInstance();
        SingletoneService singletoneService2 = SingletoneService.getInstance();

        Assertions.assertThat(singletoneService1).isSameAs(singletoneService2);
    }

    @Test
    @DisplayName("스프링 DI 컨테이너가 싱글톤 패턴인지 확인")
    void springContainerTest () {
//        AppConfig appConfig = new AppConfig();
        ApplicationContext springContainer = new AnnotationConfigApplicationContext(AppConfig.class);
        //1. 호출할때마다 객체를 생성하는지 확인
        MemberService memberService1 = springContainer.getBean("memberService", MemberService.class);
        MemberService memberService2 = springContainer.getBean("memberService", MemberService.class);

        //2. 참조값이 다른 것 확인 -> 이렇게 되면 JVM 메모리에 계속해서 인스턴스를 생성함
        System.out.println("memberService1: " + memberService1);
        System.out.println("memberService2: " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }

    @Test
    @DisplayName("정말 스프링 빈은 싱글톤일까")
    void singletonTest () {
        ApplicationContext springContainer = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = springContainer.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = springContainer.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = springContainer.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberRepository1: " + memberRepository1);
        System.out.println("memberRepository2: " + memberRepository2);
        System.out.println("memberRepository: " + memberRepository);

        Assertions.assertThat(memberRepository).isSameAs(memberRepository1);
        Assertions.assertThat(memberRepository).isSameAs(memberRepository2);

    }
}
