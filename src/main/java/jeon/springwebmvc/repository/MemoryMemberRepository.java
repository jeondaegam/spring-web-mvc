package jeon.springwebmvc.repository;

import jeon.springwebmvc.domain.Member;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemoryMemberRepository implements MemberRepository {


    /**
     * member를 찾을 key값으로 sequence를 사용
     */
    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    /**
     * Optional.ofNullable() : Java 8
     * Null이 반환될 가능성이 있으면 nullable로 감싸준다
     * 그러면 클라이언트에서 무언가 더 할 수 있다고 함.
     *
     */
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    /**
     * Lambda : Java 8
     * values(): store의 모든 member를
     * stream(): loop를 돌린다
     * .filter().findAny() : member의 이름이 name과 같은 객체를 하나라도 발견하면 바로 리턴한다.
     *
     */
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
//        return List.of(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
