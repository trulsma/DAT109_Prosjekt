package no.hvl.dat109.spring.controller;

import no.hvl.dat109.spring.service.Interfaces.IStemmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StemmeController {

    //Todo make useful controller methods

    @Autowired
    private IStemmeService stemmeService;
}
