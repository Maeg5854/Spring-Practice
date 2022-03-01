package maeg.hellospring.service;

import maeg.hellospring.domain.member;
import maeg.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() {
        //given
        member member = new member();
        member.setName("hello");

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

        Assertions.assertThat(e).isEqualTo("이미 존재하는 회원입니다.");

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