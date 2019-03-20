package no.hvl.dat109.spring.controller;

import no.hvl.dat109.spring.service.StudieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudieController {

    @Autowired
    private StudieService studieService;

    @GetMapping("/studie")
    public String getAllStudie(){

        studieService.test();
        return "index";
    }
}
