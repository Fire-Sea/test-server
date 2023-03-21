package firesea.testserver.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    String username;

    String password;

    String email;

    String nickname;

}
