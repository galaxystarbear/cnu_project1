package project.reservation;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.reservation.repository.MemberRepository;
import project.reservation.repository.MemoryMemberRepository;
import project.reservation.repository.MemoryTimeRepository;
import project.reservation.repository.TimeRepository;
import project.reservation.service.MemberService;
import project.reservation.service.TimeService;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public TimeService timeService() {
        return new TimeService(timeRepository());
    }
    
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public TimeRepository timeRepository() {
        return new MemoryTimeRepository(em);
    }
    
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository(em);
    }
}
