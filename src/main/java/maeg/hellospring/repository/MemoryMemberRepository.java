package maeg.hellospring.repository;

import maeg.hellospring.domain.member;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{
    // 실무에서는 Cocurrent map을 사용해야 함.
    // 모든 변수에서 동시성 문제를 고려해야함.
    private static Map<Long, member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public member save(member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();

    }

    @Override
    public List<member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
