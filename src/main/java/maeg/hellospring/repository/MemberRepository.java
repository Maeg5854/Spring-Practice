package maeg.hellospring.repository;

import maeg.hellospring.domain.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    member save(member member);
    Optional<member> findById(Long id);
    Optional<member> findByName(String name);
    List<member> findAll();
}
