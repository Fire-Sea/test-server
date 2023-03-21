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

    String category;

    String nickname;

    public TextMessage(String textTitle, String textBody, String category, String nickname) {
        this.textTitle = textTitle;
        this.textBody = textBody;
        this.category = category;
        this.nickname = nickname;
    }

}

