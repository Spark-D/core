package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    //추상화에도 의존, 구체화에도 의존 DIP 위반
//    MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

   @BeforeEach
   public void beforeEach() {
       AppConfig appConfig = new AppConfig();
       memberService = appConfig.memberService();
   }

    @Test
    void join (){
        //given
        Member member = new Member(1L, "memberB", Grade.VIP);

        //when
        memberService.join(member);
        memberService.findMember(1L);

        //then
        Assertions.assertThat(memberService.findMember(1L)).isEqualTo(member);
    }



}
