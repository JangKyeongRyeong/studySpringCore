package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)   // ElementType.TYPE이면 클래스 레벨에 붙는다는 뜻
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
