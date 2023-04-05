package firesea.testserver.repository;

import com.querydsl.core.Tuple;
import firesea.testserver.domain.TextMessage;
import firesea.testserver.domain.TextMessageTitleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TextMessageRepositoryCustom {

    Page<TextMessageTitleDto> getMainPageList(String category, Pageable pageable);
}
