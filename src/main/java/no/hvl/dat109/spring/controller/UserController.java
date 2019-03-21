package no.hvl.dat109.spring.controller;

import no.hvl.dat109.spring.service.UserGroupService;
import no.hvl.dat109.spring.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


    @GetMapping("/users")
    public String getAllStudie(){

        return "index";
    }
}
