package project.reservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationRestController{
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
}
