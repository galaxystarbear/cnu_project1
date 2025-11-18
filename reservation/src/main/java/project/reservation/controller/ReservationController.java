package project.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReservationController {

    @PostMapping("/main")
    public String mainPage(UserDto userDto, Model model) {
        model.addAttribute("userid", userDto.userid);
        return "main";
    }


    public class UserDto {
        private String userid;
        private String pass;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }
    }

}

