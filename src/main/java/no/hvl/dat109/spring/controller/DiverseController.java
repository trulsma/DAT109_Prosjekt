package no.hvl.dat109.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

public class DiverseController {


    @PostMapping("/registrer_deg")
    String registrerBruker(@RequestParam String epost, HttpSession session) {
        session.setAttribute("epost", epost);

        return "index";
    }


    @GetMapping("/registrer_deg")
    public String getRegistrerBruker() {
        return "registrer_deg";
    }


    @GetMapping("/index")
    String getNoe(HttpSession session) {
        if (session.getAttribute("epost") == null) {
            return "redirect:/registrer_deg";
        }
        return "index";
    }
}


