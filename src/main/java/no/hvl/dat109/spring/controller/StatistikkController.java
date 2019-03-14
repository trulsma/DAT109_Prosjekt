package no.hvl.dat109.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatistikkController {


    @GetMapping("/statistikk")
    public String getStatistics(){
        return "statistikk.html";
    }
}
