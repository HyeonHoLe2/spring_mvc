package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach // 테스트 끝날때 실행되는것
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save(){
        Member member = new Member("hello", 20);

        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll(){
        Member hello = new Member("hello", 20);
        Member hello2 = new Member("hello2", 30);

        memberRepository.save(hello);
        memberRepository.save(hello2);

        List<Member> result = memberRepository.findAll();

        assertThat(result.size()).isEqualTo(2);

        assertThat(result.get(0)).isEqualTo(hello);
        assertThat(result).contains(hello, hello2);
    }

}