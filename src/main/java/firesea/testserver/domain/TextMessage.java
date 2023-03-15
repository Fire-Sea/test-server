package firesea.testserver.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TextMessage extends JpaBaseEntity {

    @Id @GeneratedValue
    @Column(name = "textId")
    int id;

    String textTitle;

    String textBody;

    public TextMessage(String textTitle, String textBody) {
        this.textTitle = textTitle;
        this.textBody = textBody;
    }

}

