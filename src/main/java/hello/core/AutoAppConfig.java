package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(  // @Component이 붙은 클래스들을 자동으로 스캔해서 스프링 Bean에 등록해줌

        // 앞서 만들어 두었던 AppConfig의 설정 정보도 함께 등록되어서 컴포넌트 스캔 대상에서 제외
        // @Configuration 이 컴포넌트 스캔의 대상이 된 이유: @Configuration 소스코드를 열어보면
        // @Component 애노테이션이 붙어있기 때문이다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
