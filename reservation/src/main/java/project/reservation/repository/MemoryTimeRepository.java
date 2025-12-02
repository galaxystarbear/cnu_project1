package project.reservation.repository;

import project.reservation.domain.Time;

import java.util.*;

public class MemoryTimeRepository implements TimeRepository {
    private static Map<Long, Time> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Time save(Time time) {

        return time;
    }

    @Override
    public Optional<Time> findByTime(Long startTime) {
        return Optional.ofNullable(store.get(startTime));
    }

    @Override
    public Optional<Time> findByName(String name) {
        return store.values().stream()
                .filter(time -> time.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Time> findAll() {
        return new ArrayList<>(store.values());
    }
}
