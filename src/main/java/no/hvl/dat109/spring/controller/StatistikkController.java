package no.hvl.dat109.spring.controller;

import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.service.Interfaces.IBedriftService;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StatistikkController {

@Autowired
private IProsjektService prosjektService;

@Autowired
private IBedriftService bedriftService;


    //nødvending å gjøre tingene med Model?
    @GetMapping("/statistikk/{id}")
    public String getStatistics(@PathVariable("id") int id, Model model){
        ProsjektBean prosjekt = prosjektService.getProsjektById(id);

        if (prosjekt == null){
            return "error";
        }

        model.addAttribute("prosjekt", prosjekt);

        return "adminpages/statistikk/statistikk.html";
    }
}
