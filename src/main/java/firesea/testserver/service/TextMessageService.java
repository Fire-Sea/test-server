package firesea.testserver.service;

import firesea.testserver.domain.TextMessage;
import firesea.testserver.repository.TextMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TextMessageService {

    private final TextMessageRepository textMessageRepository;

    @Transactional
    public void save(TextMessage textMessage) {
        textMessageRepository.save(textMessage);
    }


    public Page<TextMessage> findAll(Pageable pageable) {
        return textMessageRepository.findAll(pageable);
    }

}

