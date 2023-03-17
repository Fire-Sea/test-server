package firesea.testserver.controller.api;


import firesea.testserver.domain.DefaultRes;
import firesea.testserver.domain.TextMessage;
import firesea.testserver.service.TextMessageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        log.info("textMessage.title = {}, textMessage.body={}, category={}", textMessage.getTextTitle(), textMessage.getTextBody(), textMessage.getCategory());


        textMessageService.save(textMessage);
        return DefaultRes.res(20000, "저장 완료", textMessage);
    }

    @PostMapping("/test")
    public DefaultRes<TextMessage> send2(@ModelAttribute TextMessage textMessage) {
        log.info("textMessage.title = {}, textMessage.body={}, category={}", textMessage.getTextTitle(), textMessage.getTextBody(), textMessage.getCategory());
        textMessageService.save(textMessage);
        return DefaultRes.res(20000, "저장 완료", textMessage);
    }


    @GetMapping("/api/list")
    public Page<TextMessageTitleDto> list(@RequestParam String category, @PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("페이징 시작");
        Page<TextMessage> page = textMessageService.findByCategory(category, pageable);
        Page<TextMessageTitleDto> list = page.map(TextMessageTitleDto::new);

        return list;
    }

    @GetMapping("/api/detail")
    public TextMessageDetailDto detail(@RequestParam String category, int id) {
        log.info("글 자세히 보기");
        TextMessage textMessage = textMessageService.findByCategoryAndId(category, id);
        TextMessageDetailDto detailDto = new TextMessageDetailDto();
        detailDto.setId(textMessage.getId());
        detailDto.setTextTitle(textMessage.getTextTitle());
        detailDto.setTextBody(textMessage.getTextBody());
        detailDto.setCreatedTime(textMessage.getCreatedTime());
        return detailDto;
    }


//    @GetMapping("/api/list/")
//    public Page<TextMessageTitleDto> serverList(@PathVariable String category, @PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
//        log.info("페이징 시작");
//        Page<TextMessage> page = textMessageService.findByCategory(category, pageable);
//        Page<TextMessageTitleDto> list = page.map(TextMessageTitleDto::new);
//
//        return list;
//    }

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


    @Data
    static class TextMessageDetailDto{
        public int id;
        public String textTitle;

        public String textBody;
        public LocalDateTime createdTime;

    }

}
