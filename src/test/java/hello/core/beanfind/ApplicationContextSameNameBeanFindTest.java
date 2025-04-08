package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberMemoryRepository;
import hello.core.member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextSameNameBeanFindTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(sameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상이면 중복 오류 발생하는지 테스트")
    void sameTypeBeanFind() {
//        MemberRepository bean = context.getBean(MemberRepository.class);
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> context.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 두개 이상이면, 빈의 이름을 지정해주면 된다")
    void sameTypeByNameBeanFind() {
        MemberRepository bean = context.getBean("memberRepository1",MemberRepository.class);
        assertThat(bean).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정타입의 빈을 모두 조회")
    void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = context.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("Key: " + key + ", Value: " + beansOfType.get(key));
        }
        System.out.println("beansOfType: " + beansOfType);
        assertThat(beansOfType).hasSize(2);
    }


    //해당 파일 scope에서 사용할 configuration
    @Configuration
    static class sameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemberMemoryRepository();
        }
        @Bean
        public MemberRepository memberRepository2() {
            return new MemberMemoryRepository();
        }
    }
}
