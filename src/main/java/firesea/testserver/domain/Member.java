package firesea.testserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.domain.Persistable;
import org.w3c.dom.Text;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(exclude = "textList")
public class Member extends JpaBaseTimeEntity implements Persistable<String> {

    @Id
    String username;

    String password;

    String email;

    String nickname;

    String refreshToken;

    @OneToMany(mappedBy = "member")
    List<TextMessage> textList = new ArrayList<>();


    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Member(String username, String password, String email, String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }


    /*
    의존관계 편의 메서드
     */
    public TextMessage createTextMessage(TextMessage textMessage) {
        this.textList.add(textMessage);
        textMessage.updateMember(this);
        return textMessage;
    }



    /*
     * 새로운 id를 구분할 수 있게 해준다
     */
    @Override
    public String getId() {
        return this.getId();
    }

    @Override
    public boolean isNew() {
        return (this.getCreatedTime() == null);
    }



}
