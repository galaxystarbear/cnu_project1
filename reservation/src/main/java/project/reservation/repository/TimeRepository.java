package project.reservation.repository;

import project.reservation.domain.Time;

import java.util.List;
import java.util.Optional;

public interface TimeRepository {
    Time save(Time time);
    Optional<Time> findByTime(String time);
    List<Time> findAll();
}
