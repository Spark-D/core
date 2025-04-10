package hello.core;

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

}
