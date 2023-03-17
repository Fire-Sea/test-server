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

    public Page<TextMessage> findByCategory(String category, Pageable pageable) {
        return textMessageRepository.findByCategory(category, pageable);
    }

    public TextMessage findByCategoryAndId(String category, int id) {

        return textMessageRepository.findByCategoryAndId(category, id);
    }
}

