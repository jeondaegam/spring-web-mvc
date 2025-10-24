package jeon.springwebmvc;

import jeon.springwebmvc.repository.MemberRepository;
import jeon.springwebmvc.repository.MemoryMemberRepository;
import jeon.springwebmvc.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
