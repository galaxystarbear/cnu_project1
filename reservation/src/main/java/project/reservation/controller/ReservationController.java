package project.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.reservation.domain.Time;
import project.reservation.domain.UserDto;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReservationController {

    @PostMapping("/main")
    public String mainPage(UserDto userDto, Model model) {
        model.addAttribute("userid", userDto.getUserid());
        return "main";
    }

    @GetMapping("/booking")
    public List<Time> getReservation() {
        List<Time> timeList = new ArrayList<>();
        timeList.add(new Time("2025-04-18", "09:00"));
        timeList.add(new Time("2025-04-18", "10:00"));
        timeList.add(new Time("2025-04-18", "11:00"));
        return timeList;
    }
}

