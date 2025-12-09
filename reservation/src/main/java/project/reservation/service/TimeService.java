package project.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import project.reservation.domain.ReservationTime;
import project.reservation.repository.TimeRepository;

import java.util.List;

public class TimeService {
    private final TimeRepository timeRepository;

    @Autowired
    public TimeService(TimeRepository timeRepository){
        this.timeRepository = timeRepository;
    }

    public String enroll(ReservationTime reservationTime) {
        timeRepository.findByTime(reservationTime.getTime())
                .ifPresent(m -> {
                    throw new IllegalStateException("예약이 불가능한 시간입니다.");
                });
        timeRepository.save(reservationTime);
        return reservationTime.getTime();
    }

    public List<ReservationTime> findTimes() {
        return timeRepository.findAll();
    }


}
