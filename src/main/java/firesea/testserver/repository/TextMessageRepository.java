package firesea.testserver.repository;

import firesea.testserver.domain.TextMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TextMessageRepository extends JpaRepository<TextMessage, Integer> {


}
