package maeg.hellospring.service;

import maeg.hellospring.domain.member;
import maeg.hellospring.repository.MemberRepository;
import maeg.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {
    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(member member) {
        validateDuplicateMember(member); //같은 이름이 있는 중복회원 X

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    
    /*
    * 회원조회
    * */
    public List<member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
