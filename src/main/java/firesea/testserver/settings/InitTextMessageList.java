package firesea.testserver.settings;

import firesea.testserver.domain.TextMessage;
import firesea.testserver.service.TextMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitTextMessageList {

    private final TextMessageService textMessageService;

    @PostConstruct
    public void init() {
        textMessageService.save(new TextMessage("프로미스나인 ", "박지원"));
        textMessageService.save(new TextMessage("프로미스나인 ", "이나경"));
        textMessageService.save(new TextMessage("프로미스나인 ", "노지선"));
        textMessageService.save(new TextMessage("프로미스나인 ", "송하영"));
        textMessageService.save(new TextMessage("프로미스나인 ", "이채영"));
        textMessageService.save(new TextMessage("프로미스나인 ", "이서연"));
        textMessageService.save(new TextMessage("프로미스나인 ", "이새롬"));
        textMessageService.save(new TextMessage("프로미스나인 ", "백지헌"));
        textMessageService.save(new TextMessage("뉴진스 ", "팜하니"));
        textMessageService.save(new TextMessage("뉴진스 ", "김민지"));
        textMessageService.save(new TextMessage("뉴진스 ", "다니엘"));
        textMessageService.save(new TextMessage("뉴진스 ", "강해린"));
        textMessageService.save(new TextMessage("뉴진스 ", "강혜인"));
        textMessageService.save(new TextMessage("테니스 ", "조코비치"));
        textMessageService.save(new TextMessage("테니스 ", "나달"));
        textMessageService.save(new TextMessage("테니스 ", "페더러"));
        log.info("PostConstruct - 기본 리스트 완성");
    }

}
