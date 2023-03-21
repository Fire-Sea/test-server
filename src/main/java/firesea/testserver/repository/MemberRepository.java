package firesea.testserver.repository;

import firesea.testserver.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    public int countMemberByUsername(String username);

    public Member findByUsername(String username);

    int countMemberByNickname(String nickname);
}
