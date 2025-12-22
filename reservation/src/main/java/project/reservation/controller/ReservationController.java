package project.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.reservation.domain.NewUserDto;
import project.reservation.domain.ReservationRequest;
import project.reservation.domain.ReservationTime;
import project.reservation.domain.UserDto;
import project.reservation.service.MemberService;
import project.reservation.service.TimeService;

import java.util.List;

@Controller
public class ReservationController {
    public final TimeService timeService;
    public final MemberService memberService;

    @Autowired
    public ReservationController(TimeService timeService, MemberService memberService) {
        this.timeService = timeService;
        this.memberService = memberService;
    }

    @PostMapping("/main")
    public String mainPage(UserDto userDto, Model model) {
        if (memberService.login(userDto.getUserid(), userDto.getPass())){
            model.addAttribute("userid", userDto.getUserid());
            return "main";
        }
        return "login";
    }
    
//    @GetMapping("/pw_change")
//    public String passChange(NewUserDto newUserDto) {
//        memberService.changePassword(newUserDto.getUserid(), newUserDto.getPass(), newUserDto.getNewPass());
//        return "pw_change";
//    }
    @GetMapping("/pw_change")
    public String passChange() {
        
        return "pw_change";
    }
    @PostMapping("/booking")
    public String getReservation(ReservationRequest reservationRequest, Model model) {
        timeService.enroll(reservationRequest);
        model.addAttribute("userid", reservationRequest.getName());
        return "main";
    }
}

