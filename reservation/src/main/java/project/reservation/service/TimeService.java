package project.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import project.reservation.domain.Time;
import project.reservation.repository.TimeRepository;

import java.util.List;

public class TimeService {
    private final TimeRepository timeRepository;

    @Autowired
    public TimeService(TimeRepository timeRepository){
        this.timeRepository = timeRepository;
    }

    public String enroll(Time time) {
        timeRepository.findByTime(time.getTime())
                .ifPresent(m -> {
                    throw new IllegalStateException("예약이 불가능한 시간입니다.");
                });
        timeRepository.save(time);
        return time.getTime();
    }

    public List<Time> findTimes() {
        return timeRepository.findAll();
    }


}
