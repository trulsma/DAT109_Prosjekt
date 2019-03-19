package no.hvl.dat109.spring.controller;

import no.hvl.dat109.spring.service.Interfaces.IArrangementdeltagelseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArrangementdeltagelseController {

    @Autowired
    private IArrangementdeltagelseService arrangementService;

    @GetMapping("/deltagelse")
    public String test() {

        //arrangementService.test();

        return "index";
    }
}
