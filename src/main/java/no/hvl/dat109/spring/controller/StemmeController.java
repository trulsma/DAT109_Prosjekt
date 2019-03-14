package no.hvl.dat109.spring.controller;

import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.beans.StemmeBean;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import no.hvl.dat109.spring.service.Interfaces.IStemmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StemmeController {

    //Todo make useful controller methods

    @Autowired
    private IStemmeService stemmeService;

    @Autowired
    private IProsjektService prosjektService;

    @GetMapping("/stem")
    public String visTakkForStemme() {
        return "takk_for_stemme";
    }

    @PostMapping("/stem")
    public String stem(@RequestParam int prosjektid, @RequestParam String epost, @RequestParam int verdi) {

        // TODO: finne riktig prosjekt for prosjekt iden

        ProsjektBean prosjekt = prosjektService.getProsjektById(prosjektid);

        if (prosjekt == null) {
            return "error";
        }

        stemmeService.addStemme(new StemmeBean(prosjekt, epost, verdi));

        return "redirect:/stem";
    }

}
