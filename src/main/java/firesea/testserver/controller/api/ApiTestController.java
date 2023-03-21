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

    private final TextMessageService textMessageService;
    private final MemberService memberService;

    @PostMapping("/api/send")
    public DefaultRes<TextMessage> send(@RequestBody TextMessage textMessage) {
        log.info("textMessage.title = {}, textMessage.body={}, category={}, nickname ={}", textMessage.getTextTitle(), textMessage.getTextBody(), textMessage.getCategory(), textMessage.getNickname());


        textMessageService.save(textMessage);
        return DefaultRes.res(20000, "저장 완료", textMessage);
    }

    @GetMapping("/api/list")
    public Page<TextMessageTitleDto> list(@RequestParam String category, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
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

    static class TextMessageTitleDto {
        public int id;
        public String textTitle;
        public LocalDateTime createdTime;

        public String nickname;

        public TextMessageTitleDto(TextMessage textMessage) {
            id = textMessage.getId();
            textTitle = textMessage.getTextTitle();
            createdTime = textMessage.getCreatedTime();
            nickname = textMessage.getNickname();
        }
    }


    @Data
    static class TextMessageDetailDto {
        public int id;
        public String textTitle;

        public String textBody;
        public LocalDateTime createdTime;

    }

    @PostMapping("/api/register")
    public DefaultRes register(@RequestBody Member member) {
        log.info("member.username = {}, member.email={}, member.password={}", member.getUsername(), member.getEmail(), member.getPassword());
        ResponseEntity<DefaultRes> responseEntity;

        DefaultRes defaultRes;
        int result = memberService.save(member);
        if (result == 1) {
            defaultRes = DefaultRes.res(20001, "회원가입이 완료되었습니다");
//            responseEntity = new ResponseEntity<>(defaultRes, HttpStatus.OK);
            log.info("회원가입 완료");
        } else {
            defaultRes = DefaultRes.res(40001, "회원가입이 실패했습니다");
//            responseEntity = new ResponseEntity<>(defaultRes, HttpStatus.BAD_REQUEST);
            log.info("회원가입 실패");
        }
        return defaultRes;
    }

    @PostMapping("/api/login")
    public DefaultRes login(@RequestBody Member member) {
        log.info("member.username = {}, member.email={}, member.password={}", member.getUsername(), member.getEmail(), member.getPassword());
        String nickname = memberService.login(member);
        DefaultRes defaultRes;
        if (!nickname.equals("")) {
            defaultRes = DefaultRes.res(20002, "로그인 성공", nickname);
        } else {
            defaultRes = DefaultRes.res(40002, "로그인 실패");
        }

        return defaultRes;
    }

    @GetMapping("/api/idCheck")
    public DefaultRes idCheck(@RequestParam("username") String username) {
        log.info("member.getUsername = {}", username);
        int count = memberService.countMemberByUsername(username);

        DefaultRes defaultRes;

        if (count == 0) {
            defaultRes = DefaultRes.res(20003, "사용가능한 아이디입니다");
        } else {
            defaultRes = DefaultRes.res(40003, "사용 중인 아이디입니다");
        }

        return defaultRes;
    }

    @GetMapping("/api/nicknameCheck")
    public DefaultRes nicknameCheck(@RequestParam("nickname") String nickname) {
        log.info("member.getNickname = {}", nickname);
        int count = memberService.countMemberByNickname(nickname);

        DefaultRes defaultRes;

        if (count == 0) {
            defaultRes = DefaultRes.res(20004, "사용가능한 닉네임입니다");
        } else {
            defaultRes = DefaultRes.res(40004, "사용 중인 닉네임입니다");
        }

        return defaultRes;
    }





}
