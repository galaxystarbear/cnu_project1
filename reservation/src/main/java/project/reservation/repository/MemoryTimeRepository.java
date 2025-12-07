package project.reservation.repository;


import jakarta.persistence.EntityManager;
import project.reservation.domain.Time;

import java.util.*;

public class MemoryTimeRepository implements TimeRepository {
    private final EntityManager em;

    public MemoryTimeRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Time save(Time time) {
        em.persist(time);
        return time;
    }

    @Override
    public Optional<Time> findByTime(String time) {
        Time time1 = em.find(Time.class, time);
        return Optional.ofNullable(time1);
    }


    @Override
    public List<Time> findAll() {
        return em.createQuery("select t form Time t", Time.class)
                .getResultList();
    }
}
