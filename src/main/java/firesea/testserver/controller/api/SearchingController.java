package firesea.testserver.controller.api;

import firesea.testserver.domain.TextMessageTitleDto;
import firesea.testserver.domain.basic.DefaultRes;
import firesea.testserver.domain.basic.PageCustomDto;
import firesea.testserver.service.SearchingService;
import firesea.testserver.service.SearchingService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SearchingController {

    private final SearchingService searchingService;
    @CrossOrigin(origins = "http://localhost")
    @PostMapping("/api/search2")
    public  PageCustomDto<TextMessageTitleDto> search(@RequestBody SearchingDto searchingDto,@PageableDefault(size= 20) Pageable pageable) {
        log.info("dto.option = {} ", searchingDto.getOption());
        log.info("dto.content = {} ", searchingDto.getContent());
        log.info("컨트롤러에서 확인11111111111111111!!");

        PageCustomDto<TextMessageTitleDto> search = searchingService.search(searchingDto.getOption(), searchingDto.getContent(), pageable);
        log.info("dto.option = {} ", searchingDto.getOption());
        log.info("dto.content = {} ", searchingDto.getContent());
        log.info("컨트롤러에서 확인!!");


        return search;
    }

    @Data
    static class SearchingDto{
        String option;
        String content;
        int page;
    }

}
