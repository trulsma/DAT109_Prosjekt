package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.utilities.UrlPaths;
import no.hvl.dat109.spring.beans.AnonymStemmeBean;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.beans.ProsjektMedStemmerBean;
import no.hvl.dat109.spring.beans.StemmeBean;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import no.hvl.dat109.spring.service.Interfaces.IStemmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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


    /*
    @GetMapping(UrlPaths.API_PROSJEKTER_STEMMER)
    ResponseEntity<?> getStemmerForProsjekt(@PathVariable("id") int id,
                                            @RequestParam(required = false) Integer steps) {
        ProsjektBean prosjekt = prosjektService.getProsjektById(id);

        if (prosjekt == null) {
            return ResponseEntity.notFound().build();
        }

        //List<AnonymStemmeBean> stemmer = prosjekt.getStemmer().stream().map(AnonymStemmeBean::new).collect(Collectors.toList());
        // TODO: fikse til å bruke arragment
        return ResponseEntity.ok().body("");
    }

    @GetMapping(UrlPaths.API_PROSJEKTER_STEMMER)
     */
        /*
    ResponseEntity<?> getStemmerForAlleProsjekt(@RequestParam(required = false) String order, @RequestParam(required = false) Integer limit) {
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

        if ("antall".equals(order)) {
            prosjekterMedStemmer = prosjekterMedStemmer.stream().sorted((a, b) -> b.getAntallStemmer() - a.getAntallStemmer()).collect(Collectors.toList());
        }
        else if ("gjennomsnitt".equals(order)) {
            prosjekterMedStemmer = prosjekterMedStemmer.stream().sorted((a, b) -> Double.compare(b.getGjennomsnittVerdi(),a.getGjennomsnittVerdi())).collect(Collectors.toList());
        }

        if (limit != null && limit > 0) {
            prosjekterMedStemmer = prosjekterMedStemmer.stream().limit(limit).collect(Collectors.toList());
        }

        return ResponseEntity.ok().body(prosjekterMedStemmer);
        return ResponseEntity.badRequest().body("meg vere sjuk og ikke bra nå");
    }
        */

    @GetMapping(UrlPaths.MINE_STEMMER)
    public String visMineStemmer(HttpSession session, Model model) {
        /*String epost = (String) session.getAttribute("epost");

        if (epost == null) {
            return "redirect:" + UrlPaths.REGISTRER_DEG_HTML + "?redirect_url=mine_stemmer";
        }

        Iterable<StemmeBean> stemmer = stemmeService.getAlleStemmer();

        List<StemmeBean> stemmerListe = new ArrayList<>();

        stemmer.forEach(stemmerListe::add);

        stemmerListe = stemmerListe.stream().filter(a -> a.getEpost().equals(epost)).collect(Collectors.toList());

        model.addAttribute("stemmer", stemmerListe);
        */

        return UrlPaths.MINE_STEMMER_HTML;
    }

    @GetMapping(UrlPaths.STEM)
    public String visTakkForStemme() {
        return UrlPaths.TAKK_FOR_STEMME_HTML;
    }

    @PostMapping(UrlPaths.STEM)
    public String stem(@RequestParam int prosjektid, @RequestParam String epost, @RequestParam int verdi) {

        ProsjektBean prosjekt = prosjektService.getProsjektById(prosjektid);

        if (prosjekt == null) {
            return UrlPaths.ERRORPAGE;
        }


        // TODO: bruke arragemetntdeltagelse
        //stemmeService.addStemme(new StemmeBean(prosjekt, epost, validateVerdi(verdi)));

        return "redirect:" + UrlPaths.STEM;
    }

    /**
     * Validerer at verdien ikke er større enn 5 eller mindre enn 0
     *
     * @param verdi verdi fra post
     * @return verdi mellom 0 og 5
     */
    private int validateVerdi(int verdi) {
        if (verdi < 0) verdi = 0;
        else if (verdi > 5) verdi = 5;
        return verdi;
    }

}
