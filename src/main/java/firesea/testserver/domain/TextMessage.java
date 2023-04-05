package firesea.testserver.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TextMessage extends JpaBaseEntity {

    @Id @GeneratedValue
    @Column(name = "textId")
    int id;

    String textTitle;

    String textBody;

    String category;

    Boolean deleteTrue = false;

    LocalDateTime deleteTime = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_username")
    Member member;

    public TextMessage(String textTitle, String textBody, String category) {
        this.textTitle = textTitle;
        this.textBody = textBody;
        this.category = category;
        this.deleteTrue = false;
        this.deleteTime = null;
    }



    /**
     * 편의 메서드
     */
    public void updateMember(Member member) {
        this.member = member;

    }

    public void updateTextMessage(String textTitle, String textBody) {

        this.textTitle = textTitle;
        this.textBody = textBody;
    }

    public void delete(int id) {
        this.deleteTrue = true;
        this.deleteTime = LocalDateTime.now();
    }


}

