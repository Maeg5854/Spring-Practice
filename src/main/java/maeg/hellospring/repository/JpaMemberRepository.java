package maeg.hellospring.repository;

import maeg.hellospring.domain.member;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public member save(member member) {
        em.persist(member); // 대박적...
        return member;
    }

    @Override
    public Optional<member> findById(Long id) {
        member member= em.find(member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<member> findByName(String name) {
        List<member> result = em.createQuery("select m from member m where m.name= :name", member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<member> findAll() {
        return em.createQuery("select m from member m", member.class)
                .getResultList();
    }
}
