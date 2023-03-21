package firesea.testserver.service;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import firesea.testserver.domain.Member;
import firesea.testserver.repository.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public int countMemberByUsername(String username) {
      return  memberRepository.countMemberByUsername(username);
    }

    @Transactional
    public int save(Member member) {
        Member savedMember = memberRepository.save(member);
        if (savedMember == null) {
            return 0;
        } else {
            return 1;
        }
    }


    public int countMemberByNickname(String nickname) {
        return  memberRepository.countMemberByNickname(nickname);
    }


    public String login(Member member) {
        Member savedMember = memberRepository.findByUsername(member.getUsername());
        String result ="";

        if (savedMember == null) {
            return result;
        }

        log.info("member.getPassword={}, savedMember.getPassword = {}", member.getPassword(), savedMember.getPassword());
        if (member.getPassword().equals(savedMember.getPassword())) {
            result = savedMember.getNickname();
        }

        return result;
    }
}
