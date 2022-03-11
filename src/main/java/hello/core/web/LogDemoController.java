package hello.core.web;
import hello.core.common.MyLogger;
import hello.core.logdemo.LogDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    /**
     *  objectProvider를 사용함으로써 MyLogger를 주입 받는게 아니라 MyLogger를 찾을수있는 (Dependency Lookup 할수있는)애가 주입 된것이다.
     *  MyLogger는 Request Scope(HTTP 요청 하나가 들어오고 나갈 때 까지 유지되는 스코프)여서
     *  스프링 컨테이너가 로딩되는 시점에 자동의존관계 주입(Autowired)을 위해 'Request Scope 빈'을 생성하려면 Scope가 맞지 않아서 오류 발생.
     *  그래서 Provider로 Request Scope이 필요한 시점에 Request Scope 빈을 찾아와(찾아봤는데 없으면 생성) 사용할 수 있게 된다
     */
    private final LogDemoService logDemoService;
    //private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}