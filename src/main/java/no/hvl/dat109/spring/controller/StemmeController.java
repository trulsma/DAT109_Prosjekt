package no.hvl.dat109.spring.controller;

import no.hvl.dat109.spring.beans.AnonymStemmeBean;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.beans.ProsjektMedStemmerBean;
import no.hvl.dat109.spring.beans.StemmeBean;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import no.hvl.dat109.spring.service.Interfaces.IStemmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StemmeController {

    @Autowired
    private IStemmeService stemmeService;

    @Autowired
    private IProsjektService prosjektService;


    @GetMapping("/api/prosjekt/{id}/stemmer")
    ResponseEntity<?> getStemmerForProsjekt(@PathVariable("id") int id) {
        ProsjektBean prosjekt = prosjektService.getProsjektById(id);

        if (prosjekt == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(prosjekt.getStemmer().stream().map(AnonymStemmeBean::new));
    }

    @GetMapping("/api/prosjekter/stemmer")
    ResponseEntity<?> getStemmerForAlleProsjekt() {
        Iterable<ProsjektBean> prosjekter = prosjektService.getAlleProsjekter();

        List<ProsjektBean> prosjektListe = new ArrayList<>();

        prosjekter.forEach(prosjektListe::add);


        List<ProsjektMedStemmerBean> prosjekterMedStemmer = prosjektListe.stream().map(prosjekt ->
                new ProsjektMedStemmerBean(prosjekt.getProsjektid(),
                        prosjekt.getProsjektnavn(),
                        prosjekt.getProsjektbeskrivelse(),
                        prosjekt.getStemmer().size(),
                        prosjekt.getStemmeGjennomsnitt()))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(prosjekterMedStemmer);
    }

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

        stemmeService.addStemme(new StemmeBean(prosjekt, epost, validateVerdi(verdi)));

        return "redirect:/stem";
    }

    /**
     * Validerer at verdien ikke er større enn 5 eller mindre enn 0
     * @param verdi verdi fra post
     * @return verdi mellom 0 og 5
     */
    private int validateVerdi(int verdi) {
        if (verdi < 0) verdi = 0;
        else if (verdi > 5) verdi = 5;
        return verdi;
    }

}
