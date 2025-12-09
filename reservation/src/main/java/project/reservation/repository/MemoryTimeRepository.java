package project.reservation.repository;


import jakarta.persistence.EntityManager;
import project.reservation.domain.ReservationTime;

import java.sql.Time;
import java.util.*;

public class MemoryTimeRepository implements TimeRepository {
    private final EntityManager em;

    public MemoryTimeRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public ReservationTime save(ReservationTime reservationTime) {
        em.persist(reservationTime);
        return reservationTime;
    }

    @Override
    public Optional<ReservationTime> findByTime(String time) {
        List<ReservationTime> result = em.createQuery("select rt from ReservationTime rt where rt.time = :time", ReservationTime.class)
                .setParameter("time", time)
                .getResultList();

        return result.stream().findAny();
    }


    @Override
    public List<ReservationTime> findAll() {
        return em.createQuery("select rt from ReservationTime rt", ReservationTime.class)
                .getResultList();
    }
}
