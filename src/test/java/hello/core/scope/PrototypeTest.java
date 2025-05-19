package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

    @Test
    public void PrototypeTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean = context.getBean(PrototypeBean.class);
        PrototypeBean bean2 = context.getBean(PrototypeBean.class);

        System.out.println("bean = " + bean);
        System.out.println("bean2 = " + bean2);
        Assertions.assertThat(bean).isNotSameAs(bean2);
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean init");
        }

        @PreDestroy
        public void destroy() {
            //안찍힘
            //프로토타입 스코프 빈은 스프링 컨테이너가 생성과 의존관계 주입 그리고 초기화까지만 관여하고, 더 관리하지 않는다.
            // 프로토타입 빈은 스프링컨테이너가 종료될때 @Predestroy와 같은 종료메서드가 전혀 실행 되지 않는다
            System.out.println("PrototypeBean destroy");
        }
    }

}
