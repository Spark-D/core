package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {

    @Test
    public void singletonTest() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletoneBean.class);
        SingletoneBean bean = ac.getBean(SingletoneBean.class);
        SingletoneBean bean2 = ac.getBean(SingletoneBean.class);
        System.out.println("bean = " + bean);
        System.out.println("bean2 = " + bean2);

        Assertions.assertThat(bean).isEqualTo(bean2);

        ac.close();
    }

    @Scope("singleton")
    static class SingletoneBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletoneBean init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletoneBean destroy");
        }
    }
}
