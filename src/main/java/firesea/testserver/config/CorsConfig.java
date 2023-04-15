package firesea.testserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

@Configuration
@Slf4j
public class CorsConfig {
    // 인증이 필요하지 않은 요청만 허용한다면, @CrossOrigin 으로 해결 가능
    // But! 인증이 필요한 요청까지 허용하려면, 필터를 직접 설정해주어야 한다.
//    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 내 서버가 응답을 할 때, json을 javascript에서 처리할 수 있게 할지를 설정
        config.addAllowedHeader("*"); //모든 header에 응답을 허용하겠다.
        config.addAllowedOrigin("http://localhost");
//        config.addAllowedOrigin("http://14.34.46.23");
//        config.addAllowedOrigin("http://14.34.46.23:80");
//        config.addAllowedOrigin("http://172.30.1.29");
//        config.addAllowedOriginPattern("*"); //모든 ip에 응답을 허용하겠다
        config.addAllowedMethod("*"); //모든 http 메서드 요청을 허용하겠다.
        config.addExposedHeader("access_token");
        config.addExposedHeader("refresh_token");
        config.addExposedHeader("content-type");
        config.addExposedHeader("Set-Cookie");
        source.registerCorsConfiguration("/api/**",config);

        return new CorsFilterImpl(source);
    }

    static class CorsFilterImpl extends CorsFilter {
        /**
         * Constructor accepting a {@link CorsConfigurationSource} used by the filter
         * to find the {@link CorsConfiguration} to use for each incoming request.
         *
         * @param configSource
         * @see UrlBasedCorsConfigurationSource
         */
        public CorsFilterImpl(CorsConfigurationSource configSource) {
            super(configSource);
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            log.info("preflight test");
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String header = headerNames.nextElement();
                log.info("header = {} ", header);
                log.info("header values = {} ", request.getHeader(header));
            }

            log.info("url = {}", request.getRequestURL());
            log.info("method = {}", request.getMethod());

            super.doFilterInternal(request, response, filterChain);
        }
    }

}
