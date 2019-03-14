package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.Processing;
import no.hvl.dat109.spring.beans.BedriftBean;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.service.Interfaces.IBedriftService;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProsjektController {

    //Todo make useful controller methods

    @Autowired
    private IProsjektService prosjektService;

    @Autowired
    private IBedriftService bedriftService;

    @GetMapping("/prosjekter")
    String getAlleProsjekter(Model model) {
        model.addAttribute("prosjekter", prosjektService.getAlleProsjekter());

        return "prosjekter.html";
    }

    @GetMapping("/prosjekt/{id}/qr")
    String getProsjektQR(@PathVariable("id") int id, Model model) {

        ProsjektBean prosjekt = prosjektService.getProsjektById(id);

        if (prosjekt == null) {
            return "error";
        }

        model.addAttribute("qrfil", "images/" + prosjekt.getProsjektid()
                + "_" + prosjekt.getProsjektnavn().replaceAll(" ", "_") + ".png");

        return "qrkode";
    }

    @GetMapping("/prosjekt/{id}/qr/create")
    String createProsjektQR(@PathVariable("id") int id, Model model) {

        ProsjektBean prosjekt = prosjektService.getProsjektById(id);

        if (prosjekt == null) {
            return "error";
        }

        Processing processing = new Processing();
        processing.createQRCode(prosjekt);

        // OBS! serveren kan redirecte før qrkoden bildet er lagret og vil ikke være oppdattert uten er refresh
        return "redirect:/prosjekt/" + id + "/qr";
    }

    @GetMapping("/prosjekt/{id}")
    String getProsjektById(@PathVariable("id") int id, Model model) {

        ProsjektBean prosjekt = prosjektService.getProsjektById(id);

        if (prosjekt == null) {
            return "error";
        }
        BedriftBean samarbeidspartner = bedriftService.getBedriftById(prosjekt.getSamarbeidsbedrift());

        model.addAttribute("samarbeidspartner", samarbeidspartner);
        model.addAttribute("prosjekt", prosjekt);

        return "prosjekt";

    }

    @GetMapping("/prosjekt/add")
    String addProsjekt(Model model) {
        model.addAttribute("bedrifter", bedriftService.getAlleBedrifter());
        return "registrer_prosjekt";
    }

    @PostMapping("/prosjekt/add")
    String addProsjektPostRequest(
            @RequestParam String prosjektnavn,
            @RequestParam String prosjektbeskrivelse,
            @RequestParam int samarbeidspartner) {
        //  System.out.println(id);


        ProsjektBean prosjekt = new ProsjektBean(prosjektnavn, prosjektbeskrivelse, samarbeidspartner, "");

        prosjektService.addProsjekt(prosjekt);

        Processing processing = new Processing();

        processing.createQRCode(prosjekt);

        return "redirect:/prosjekt/" + prosjekt.getProsjektid();
    }
}
