package no.hvl.dat109.spring.controller;

import no.hvl.dat109.spring.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserGroupController {
    @Autowired
    private UserGroupService userGroupService;

    @GetMapping("/usergroup")
    public String getAllStudie(){

        userGroupService.test();
        return "index";
    }
}
