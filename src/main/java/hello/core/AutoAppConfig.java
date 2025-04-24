package hello.core;

import hello.core.member.MemberMemoryRepository;
import hello.core.member.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 컴포넌트 스캔을 시작할 패키지를 지정할수 있다, 지정하지 않으면 설정정보 클래스의 패키지가 컴포넌트 스캔의 베이스페키지가 된다(관례 사용 권장)
//        basePackages = "hello.core.member",
        // @Configuration은 빼고 컴포넌트 스캔이 되도록 설정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Configuration.class})
)
public class AutoAppConfig {

    //자동 등록 빈 vs. 수동 등록 빈 이름이 충돌날때 에러발생하지는 않으나, 수동 등록빈이 자동등록빈을 override 한다! 수동이 이김!
//    @Bean(name = "memberMemoryRepository")
//    MemberRepository memberRepository () {
//        return new MemberMemoryRepository();
//    }
}
