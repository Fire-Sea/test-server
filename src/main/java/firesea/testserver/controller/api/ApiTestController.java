package firesea.testserver.controller.api;


import firesea.testserver.domain.DefaultRes;
import firesea.testserver.domain.Member;
import firesea.testserver.domain.TextMessage;
import firesea.testserver.service.MemberService;
import firesea.testserver.service.TextMessageService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Text;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiTestController {

    @GetMapping("/api/test")
    public String test() {
        return "hello firesea";
    }


}
