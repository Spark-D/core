package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    //추상화에도 의존, 구체화에도 의존 DIP 위반
    MemberService memberService = new MemberServiceImpl();

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
