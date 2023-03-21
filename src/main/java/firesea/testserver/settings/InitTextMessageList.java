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
        textMessageService.save(new TextMessage("프로미스나인 ", "박지원", Category.SERVER, "플로버1"));
        textMessageService.save(new TextMessage("프로미스나인 ", "이나경", Category.FRONT, "플로버2"));
        textMessageService.save(new TextMessage("프로미스나인 ", "노지선", Category.SERVER, "플로버3"));
        textMessageService.save(new TextMessage("프로미스나인 ", "송하영", Category.FRONT, "플로버4"));
        textMessageService.save(new TextMessage("프로미스나인 ", "이채영", Category.SERVER, "플로버5"));
        textMessageService.save(new TextMessage("프로미스나인 ", "이서연", Category.SERVER,"플로버6"));
        textMessageService.save(new TextMessage("프로미스나인 ", "이새롬", Category.FRONT, "플로버7"));
        textMessageService.save(new TextMessage("프로미스나인 ", "백지헌", Category.SERVER, "플로버8"));
        textMessageService.save(new TextMessage("뉴진스 ", "팜하니", Category.FRONT, "버니즈1"));
        textMessageService.save(new TextMessage("뉴진스 ", "김민지", Category.SERVER,"버니즈2"));
        textMessageService.save(new TextMessage("뉴진스 ", "다니엘", Category.FRONT,"버니즈3"));
        textMessageService.save(new TextMessage("뉴진스 ", "강해린", Category.SERVER,"버니즈4"));
        textMessageService.save(new TextMessage("뉴진스 ", "강혜인", Category.FRONT, "버니즈5"));
        textMessageService.save(new TextMessage("테니스 ", "조코비치", Category.SERVER, "서버개발자"));
        textMessageService.save(new TextMessage("테니스 ", "나달", Category.FRONT, "프론트개발자"));
        textMessageService.save(new TextMessage("테니스 ", "페더러", Category.SERVER, "아모른직다"));
        log.info("PostConstruct - 기본 리스트 완성");
    }

}
