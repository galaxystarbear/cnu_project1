package project.reservation.service;

import project.reservation.domain.Time;
import project.reservation.repository.TimeRepository;

import java.util.List;

public class TimeService {
    private final TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository){
        this.timeRepository = timeRepository;
    }

    public boolean enroll(Time time) {
        validateReservationTime(time);
        timeRepository.save(time);
        return true;
    }

    private void validateReservationTime(Time time) {
        timeRepository.findByTime(time.getStartTime() - 30)
                .ifPresent(m -> {
                    if (m.getName().equals(time.getName())) {
                        throw new IllegalStateException("연속으로 예약할 수 없습니다.");
                    }
                });
    }

    public List<Time> findTimes() {
        return timeRepository.findAll();
    }


}
