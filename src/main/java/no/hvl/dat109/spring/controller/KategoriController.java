package no.hvl.dat109.spring.controller;

import no.hvl.dat109.spring.service.KategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KategoriController {

    @Autowired
    private KategoriService kategoriService;

    @GetMapping("/printkategorier")
    public String printAlleKategorier() {

        System.out.println(kategoriService.getAllKategorier());

        return "index";
    }
}
