package project.reservation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import project.reservation.domain.Time;
import project.reservation.repository.TimeRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:jdbc:postgresql://db.dqrdgsdmiduieegxujor.supabase.co:5432/postgres",
        "spring.datasource.username=postgres",
        "spring.datasource.password=minjunlove",
        "spring.datasource.driver-class-name=org.postgresql.Driver",
        "spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect"
})
public class TimeServiceTest {

    @Autowired TimeService timeService;
    @Autowired
    TimeRepository timeRepository;

    @Test
    @Commit
    void 예약() {
        Time time = new Time();
        time.setTime("17:30");

        String saveTime = timeService.enroll(time);

    }

    @Test
    public void 중복예약() {
        Time time1 = new Time();
        time1.setTime("16:00");

        Time time2 = new Time();
        time2.setTime("16:00");

        timeService.enroll(time1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> timeService.enroll(time2));

        assertThat(e.getMessage()).isEqualTo("예약이 불가능한 시간입니다.");
    }
}
