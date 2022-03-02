package maeg.hellospring.service;

import maeg.hellospring.domain.member;
import maeg.hellospring.repository.MemberRepository;
import maeg.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    // 필드 주입은 원래는 권장하지 않지만, 테스트시에는 가장 빠르게 할 수 있는 방법으로 수행한다!
    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    //Commit
    void 회원가입() {
        //given
        member member = new member();
        member.setName("spring1");

        //when
        Long saveId = memberService.join(member);

        //then
        member findmember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findmember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        member member1 = new member();
        member1.setName("spring");

        member member2 = new member();
        member2.setName("spring");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*try{
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        //when

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}