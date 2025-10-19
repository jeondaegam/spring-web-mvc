package jeon.springwebmvc.repository;

import jeon.springwebmvc.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    /**
     * Optional : Java 8
     * 데이터 조회시 Null이 반환될 수 있는 경우 그대로 Null을 반환하지 않고
     * Optional 이라는 것으로 감싸서 반환하는 방법을 선호한다.
     */
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
