package project.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import project.reservation.domain.ReservationRequest;
import project.reservation.domain.ReservationTime;
import project.reservation.repository.TimeRepository;

import java.time.LocalDate;
import java.util.List;

@Transactional
public class TimeService {
    private final TimeRepository timeRepository;

    @Autowired
    public TimeService(TimeRepository timeRepository){
        this.timeRepository = timeRepository;
    }

    public String enroll(ReservationRequest reservationRequest) {
        timeRepository.findByTime(LocalDate.now().toString(), reservationRequest.getTime())
                .ifPresent(m -> {
                    throw new IllegalStateException("예약이 불가능한 시간입니다.");
                });
        System.out.println(reservationRequest.getName());
        ReservationTime rt = new ReservationTime();
        rt.setName(reservationRequest.getName());
        rt.setTime(reservationRequest.getTime());
        rt.setDate(LocalDate.now().toString());
        timeRepository.save(rt);
        return reservationRequest.getTime();
    }
    
    public List<ReservationTime> findTimes() {
        String today = LocalDate.now().toString();
        return timeRepository.findByDate(today);
    }


}
