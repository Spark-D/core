package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    public void AutoWiredOption() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false) // 의존관계가 없으면 메서드 자체가 호출이 안됨
        public void setNoBean1(Member member){
            System.out.println("member1 : " + member); // Member는 스프링빈이 아님
        }

        @Autowired
        public void setNoBean2(@Nullable Member member2){
            System.out.println("member2 : " + member2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member3){
            System.out.println("member3 : " + member3);
        }


    }
}
