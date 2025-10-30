package jeon.springwebmvc.service;

import jeon.springwebmvc.domain.Member;
import jeon.springwebmvc.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository repository;

//    @BeforeEach
//    void beforeEach() {
//        repository = new MemoryMemberRepository();
//        memberService = new MemberService(repository);
//    }


//    @AfterEach
//    void afterEach() {
//        repository.clearStore();
//    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("test-member");
        // when
        Long savedId = memberService.join(member);
        // than
        Member result = memberService.findOne(savedId).get();
        assertThat(result.getName()).isEqualTo(member.getName());
    }

    @Test
    void 중복_회원_예외처리() {

        // given
        Member member1 = new Member();
        member1.setName("member");

        Member member2 = new Member();
        member2.setName("member");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)); // 예외가 발생해야 한다.

        // than
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}