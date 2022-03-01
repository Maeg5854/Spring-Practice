package maeg.hellospring.repository;

import maeg.hellospring.domain.member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

// public으로 하지 않아도 된다.
class MemoryMemberRepositoryTest {
    MemberRepository repository = new MemoryMemberRepository() ;

    @Test
    public void save(){
        member member = new member();
        member.setName("spring");

        repository.save(member);
        member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(result, member);
    }

    @Test
    public void findByName() {
        member member1 = new member();
        member1.setName("spring1");
        repository.save(member1);

        member member2 = new member();
        member2.setName("spring2");
        repository.save(member2);

        member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }
}
