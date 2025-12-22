package project.reservation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import project.reservation.domain.ReservationTime;
import project.reservation.repository.TimeRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class TimeServiceTest {

    @Autowired TimeService timeService;
    @Autowired
    TimeRepository timeRepository;

//    @Test
//    void 예약() {
//        ReservationTime reservationTime = new ReservationTime();
//        reservationTime.setDate("2025-12-08");
//        reservationTime.setTime("17:40");
//
//        String saveTime = timeService.enroll(reservationTime);
//
//        System.out.println(saveTime);
//    }

//    @Test
//    public void 중복예약() {
//        ReservationTime reservationTime1 = new ReservationTime();
//        reservationTime1.setDate("2025-12-08");
//        reservationTime1.setTime("16:00");
//
//        ReservationTime reservationTime2 = new ReservationTime();
//        reservationTime2.setDate("2025-12-08");
//        reservationTime2.setTime("16:00");
//
//        timeService.enroll(reservationTime1);
//        IllegalStateException e = assertThrows(IllegalStateException.class, () -> timeService.enroll(reservationTime2));
//
//        assertThat(e.getMessage()).isEqualTo("예약이 불가능한 시간입니다.");
//    }
}
