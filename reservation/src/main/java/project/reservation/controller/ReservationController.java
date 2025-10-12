package project.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {

    @GetMapping("loginpage")
    public String loginpage(Model model) {
        model.addAttribute("data", "이름");
        return "loginpage";
    }
}
