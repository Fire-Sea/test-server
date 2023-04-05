package firesea.testserver.service;

import com.fasterxml.classmate.MemberResolver;
import com.querydsl.core.Tuple;
import firesea.testserver.controller.api.TextMessageController;
import firesea.testserver.domain.Member;
import firesea.testserver.domain.PageCustomDto;
import firesea.testserver.domain.TextMessage;
import firesea.testserver.domain.TextMessageTitleDto;
import firesea.testserver.repository.MemberRepository;
import firesea.testserver.repository.TextMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TextMessageService {

    private final TextMessageRepository textMessageRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void save(TextMessage textMessage, String username) {

        Member member = memberRepository.findMemberByUsername(username);
        TextMessage textMessageByMember = member.createTextMessage(textMessage);

        textMessageRepository.save(textMessageByMember);
    }


    public Page<TextMessage> findAll(Pageable pageable) {
        return textMessageRepository.findAll(pageable);
    }

    public PageCustomDto<TextMessageTitleDto> getMainPageList(String category, Pageable pageable) {
        Page<TextMessageTitleDto> mainPageList = textMessageRepository.getMainPageList(category, pageable);
        List<TextMessageTitleDto> content = mainPageList.getContent();
        int totalPages = mainPageList.getTotalPages();
        long totalElements = mainPageList.getTotalElements();
        int size = mainPageList.getSize();

        log.info("totalPage = {}", totalPages);
        log.info("totalElements = {}", totalElements);
        log.info("size = {}", size);
        for (TextMessageTitleDto dto : content) {
            log.info("dto = {} ", dto.getTextTitle());
            log.info("nickname = {}", dto.getNickname());

        }

        PageCustomDto<TextMessageTitleDto> customPage = new PageCustomDto<>(content, totalPages, totalElements, size);

        return customPage;
    }

    public TextMessage detailTextMessage(String category, int id) {

        TextMessage byCategoryAndId = textMessageRepository.findByCategoryAndId(category, id);
//        Member member = byCategoryAndId.getMember();
//        log.info("member.username = {}", member.getUsername());
//        log.info("member.nickname = {}", member.getNickname());
//
//        log.info("memebr = {}", byCategoryAndId.getMember());

        return byCategoryAndId;
    }

    @Transactional
    public void update(int id, String textTitle, String textBody) {
        TextMessage textMessage = textMessageRepository.findById(id).get();
        textMessage.updateTextMessage(textTitle, textBody);


    }

    @Transactional
    public void delete(int id) {
        TextMessage textMessage = textMessageRepository.findById(id).get();
        textMessage.delete(id);

    }
}

