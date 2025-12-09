package project.reservation.repository;

import project.reservation.domain.ReservationTime;

import java.util.List;
import java.util.Optional;

public interface TimeRepository {
    ReservationTime save(ReservationTime reservationTime);
    Optional<ReservationTime> findByTime(String time);
    List<ReservationTime> findAll();
}
