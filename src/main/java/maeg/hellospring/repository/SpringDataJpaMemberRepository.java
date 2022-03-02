package maeg.hellospring.repository;

import maeg.hellospring.domain.member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<member, Long>, MemberRepository {

    @Override
    Optional<member> findByName(String name);
}
