package no.hvl.dat109.spring.controller;

import no.hvl.dat109.spring.beans.BedriftBean;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.service.Interfaces.IBedriftService;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import static no.hvl.dat109.prosjekt.Processing.generateShortlink;
import static no.hvl.dat109.prosjekt.Processing.getImagePath;

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

        return "adminpages/prosjekter.html";
    }

    @GetMapping("/prosjekt/{id}/qr")
    String getProsjektQR(@PathVariable("id") int id, Model model) {

        ProsjektBean prosjekt = prosjektService.getProsjektById(id);

        if (prosjekt == null) {
            return "error";
        }

        model.addAttribute("qrfil", getImagePath(prosjekt));

        return "standpages/qrkode";
    }

    @GetMapping("/prosjekt/{id}/stemmer")
    ResponseEntity<?> getProsjektQR(@PathVariable("id") int id) {
        ProsjektBean prosjekt = prosjektService.getProsjektById(id);

        if (prosjekt == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(prosjekt.getStemmer());
    }

    @GetMapping("/prosjekt/{id}/qr/create")
    String createProsjektQR(@PathVariable("id") int id) {

        ProsjektBean prosjekt = prosjektService.getProsjektById(id);

        if (prosjekt == null) {
            return "error";
        }

        setQrLink(prosjekt);

        // OBS! serveren kan redirecte før qrkoden bildet er lagret og vil ikke være oppdattert uten er refresh
        return "redirect:/standpages/prosjekt/" + id + "/qr";
    }

    @GetMapping("/prosjekt/{id}")
    String getProsjektById(@PathVariable("id") int id, Model model, HttpSession session) {

        if (session.getAttribute("epost") == null) {
            return "redirect:/registrer_deg?redirect_url=" + "/prosjekt/" + id;
        }

        ProsjektBean prosjekt = prosjektService.getProsjektById(id);

        if (prosjekt == null) {
            return "error";
        }

        // RIP in peace System.out.println(prosjekt.getSammarbeidsbedrift() + " THIS IS BEDRIFT BOYYY");

        model.addAttribute("samarbeidspartner", prosjekt.getSammarbeidsbedrift());
        model.addAttribute("prosjekt", prosjekt);

        return "standpages/prosjekt";
    }

    @GetMapping("/prosjekt/add")
    String addProsjekt(Model model) {
        model.addAttribute("bedrifter", bedriftService.getAlleBedrifter());
        return "adminpages/registrering/registrer_prosjekt";
    }

    @PostMapping("/prosjekt/add")
    String addProsjektPostRequest(
            @RequestParam String prosjektnavn,
            @RequestParam String prosjektbeskrivelse,
            @RequestParam int samarbeidspartner) {
        //  System.out.println(id);

        //Finn en bedrift fra id-en til comboboxen
        BedriftBean bedrift = bedriftService.getBedriftById(samarbeidspartner);
        ProsjektBean prosjekt = new ProsjektBean(prosjektnavn, prosjektbeskrivelse, bedrift);
        prosjektService.addProsjekt(prosjekt);
        setQrLink(prosjekt);

        return "redirect:/prosjekt/" + prosjekt.getProsjektid();
    }

    private void setQrLink(ProsjektBean prosjekt) {
        prosjekt.setShortenedurl(generateShortlink(prosjekt));
        prosjekt.setQrimagepath(getImagePath(prosjekt));
        prosjektService.updateProsjekt(prosjekt);
    }
}
