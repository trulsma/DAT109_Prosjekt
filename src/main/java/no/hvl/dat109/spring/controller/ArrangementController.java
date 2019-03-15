package no.hvl.dat109.spring.controller;

import no.hvl.dat109.spring.service.ArrangementService;
import no.hvl.dat109.spring.service.Interfaces.IArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArrangementController {

    @Autowired
    private IArrangementService arrangementService;

    @GetMapping("/arrangement")
    public String test() {

        arrangementService.test();

        return "index";
    }
}
