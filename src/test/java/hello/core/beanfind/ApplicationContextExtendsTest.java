package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(testConfig.class);
    
    @Test
    @DisplayName("부모 이름으로 빈 찾기")
    void findBeanByParentType () {
        Map<String, DiscountPolicy> beansOfType = context.getBeansOfType(DiscountPolicy.class);
        for (String beanName : beansOfType.keySet()) {
            System.out.println("key: " + beanName + ", value: " + beansOfType.get(beanName));
        }
    }

    @Test
    @DisplayName("부모 타입으로 빈 조회시 중복 오류가 난다")
    void noUniquBeanByParentType () {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> context.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 빈 조회시 중복인경우 이름을 지정해주면 된다")
    void uniquBeanByNameParentType () {
        DiscountPolicy rateDiscountPolicy = context.getBean("rateDiscountPolicy", DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);
    }

    @Test
    @DisplayName("또는 하위 타입으로 빈을 조회하면된다")
    void uniquBeanByChildType () {
        DiscountPolicy rateDiscountPolicy = context.getBean("rateDiscountPolicy", RateDiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("Object 타입으로 빈을 조회해보기")
    void findBeanByObjectType () {
        Map<String, Object> beansOfType = context.getBeansOfType(Object.class);
        for (String beanName : beansOfType.keySet()) {
            System.out.println("key: " + beanName + ", value: " + beansOfType.get(beanName));
        }

    }

    @Configuration
    static class testConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixedDiscountPolicy() {
            return new FixDiscountPolicy();
        }

    }
}
