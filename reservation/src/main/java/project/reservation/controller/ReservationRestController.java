package project.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.reservation.domain.ReservationTime;
import project.reservation.service.TimeService;

import java.util.List;

@RestController
public class ReservationRestController{
    public final TimeService timeService;
    
    @Autowired
    public ReservationRestController(TimeService timeService) {
        this.timeService = timeService;
    }
    
    @GetMapping("/kimmjBabo")
    public String dumi1() {
        return "dumi1";
    }

    @GetMapping("/kimmjgenius")
    public String dumi2() {
        return "dumi2";
    }

    @GetMapping("/kimmjThatIsEatingPringles")
    public String dumi3() {
        return "dumi3";
    }
    
    @GetMapping("/booking")
    public List<ReservationTime> getReservation(ReservationTime reservationTime) {
        timeService.enroll(reservationTime);
        List<ReservationTime> timeList = timeService.findTimes();
        return timeList;
    }
}
