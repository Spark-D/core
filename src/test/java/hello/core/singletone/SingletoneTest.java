package hello.core.singletone;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
