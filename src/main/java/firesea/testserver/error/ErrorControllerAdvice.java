package firesea.testserver.error;

import firesea.testserver.domain.DefaultRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorControllerAdvice {


    @ExceptionHandler(DuplicateNickname.class)
    public DefaultRes duplicateNickname(DuplicateNickname e) {
        log.error("[duplicate nickname]", e);
        return DefaultRes.res(40017, e.getMessage());
    }
}
