package jeon.springwebmvc.repository;

import jeon.springwebmvc.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * 각 테스트 케이스는 독립적으로 실행될 수 있어야 한다.
 * 테스트 케이스를 작성할 때 실행 순서에 의존적으로 작성하면 안된다!!
 *
 */
public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();


    /**
     * 각 테스트가 종료될 때마다 실행할 작업을 작성한다.
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        // given
        Member member = new Member();
        member.setName("member1");

        // when
        repository.save(member);

        // then
        // 저장한 member의 id로 회원 정보를 조회
        Member result = repository.findById(member.getId()).get(); // get(): null을 그대로 반환하는 역할인가?
        // 위에서 입력한 name과 일치하는가?
        assertThat(result).isEqualTo(member);
    }

    @Test
    void findByName() {
        // given
        // 1. member 데이터를 저장한다.
        Member member1 = new Member();
        member1.setName("member1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("member2");
        repository.save(member2);

        // when
        // 2. 그것을 'name' 으로 조회했을 때
        Member result = repository.findByName(member1.getName()).get();

        // then
        // 3. 방금 저장한 데이터와 일치하는가?
        assertThat(result).isEqualTo(member1);

    }

    @Test
    void findAll() {
        // given
        // 1. member 데이터를 저장한다.
        Member member1 = new Member();
        member1.setName("member1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("member2");
        repository.save(member2);

        // when
        // 2.전체 리스트를 조회한다.
        List<Member> members = repository.findAll();

        // then
        // 3. 결과데이터의 개수가 save한 개수와 동일한가?
        assertThat(members).hasSize(2);

    }

}
