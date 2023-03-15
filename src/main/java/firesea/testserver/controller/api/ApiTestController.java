package firesea.testserver.controller.api;


import firesea.testserver.domain.DefaultRes;
import firesea.testserver.domain.TextMessage;
import firesea.testserver.service.TextMessageService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Text;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiTestController {

    private final TextMessageService textMessageService;

    @PostMapping("/api/send")
    public DefaultRes<TextMessage> send(@RequestBody TextMessage textMessage) {
        log.info("text = {}, {}", textMessage.getTextTitle(), textMessage.getTextBody());
        log.info("textMessage.title = {}, textMessage.body={}", textMessage.getTextTitle(), textMessage.getTextBody());
        textMessageService.save(textMessage);
        return DefaultRes.res(200000, "저장 완료", textMessage);
    }


    @GetMapping("/api/list")
    public Page<TextMessageTitleDto> list(@PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("페이징 시작");
        Page<TextMessage> page = textMessageService.findAll(pageable);
        Page<TextMessageTitleDto> list = page.map(TextMessageTitleDto::new);

        return list;
    }
    static class TextMessageTitleDto{
        public int id;
        public String textTitle;
        public LocalDateTime createdTime;

        public TextMessageTitleDto(TextMessage textMessage) {
            id = textMessage.getId();
            textTitle = textMessage.getTextTitle();
            createdTime = textMessage.getCreatedTime();
        }
    }


}
