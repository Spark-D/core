package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    //main method로 확인하는 것은 한계가 있다. 좋지 않은 방법. TDD를 활용하라
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        //ApplicationContext = 스프링컨테이너 이고, @Configuration 어노테이션이 붙은 AppConfig를 설정정보로 사용한다 
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //AppConfig에 @Bean이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다 (스프링빈)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        
        Member findMember = memberService.findMember(1L);
        System.out.println("new member: " +  member.getName());
        System.out.println("find member: " + findMember.getName());
    }
}
