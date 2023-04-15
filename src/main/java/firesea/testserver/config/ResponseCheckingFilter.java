package firesea.testserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

@Slf4j
@Component
@Order(2)
public class ResponseCheckingFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {



        log.info("필터 작동 순서 2");



        chain.doFilter(request, response);

        HttpServletRequest request1 = (HttpServletRequest) request;

        log.info("response 필터");
        HttpServletResponse response1 = (HttpServletResponse) response;
        Collection<String> headerNames = response1.getHeaderNames();
        for (String headerName : headerNames) {
            log.info("{}  = {}", headerName, response1.getHeader(headerName));
        }
        log.info("response 필터 끝");

        if (response1.getHeader("Access-Control-Allow-Origin")== null) {
            response1.setHeader("Access-Control-Allow-Origin", "http://localhost");
            log.info("실행됐다1");
        }
        if (response1.getHeader("Access-Control-Allow-Credentials")== null) {
            response1.setHeader("Access-Control-Allow-Credentials", "true");
            log.info("실행됐다2");

        }
        if (response1.getHeader("Access-Control-Allow-Methods")== null) {
            response1.setHeader("Access-Control-Allow-Methods","*");
            log.info("실행됐다3");

        }
        if (response1.getHeader("Access-Control-Allow-Headers")== null) {
            response1.setHeader("Access-Control-Allow-Headers",
                    "*");
            log.info("실행됐다4");

        }

        log.info("흐음 = {}", response1.getHeader("Access-Control-Allow-Origin"));
        log.info("흐음 = {}", response1.getHeader("Access-Control-Allow-Credentials"));
        log.info("흐음2 = {}", response1.getHeader("Access-Control-Allow-Methods"));
        log.info("흐음2 = {}", response1.getHeader("Access-Control-Allow-Headers"));

    }

}
