package project.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.reservation.domain.ReservationTime;
import project.reservation.domain.UserDto;
import project.reservation.service.TimeService;

import java.util.List;

@Controller
public class ReservationController {
    public final TimeService timeService;

    @Autowired
    public ReservationController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping("/main")
    public String mainPage(UserDto userDto, Model model) {
        model.addAttribute("userid", userDto.getUserid());
        return "main";
    }

}

