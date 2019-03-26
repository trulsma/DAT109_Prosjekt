package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.utilities.UrlPaths;
import no.hvl.dat109.spring.beans.*;
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
    public String visTakkForStemme(@RequestParam(required = false) String navn, Model model) {
        if (navn == null) {
            navn = "";
        }

        model.addAttribute("prosjektnavn", navn);

        return UrlPaths.TAKK_FOR_STEMME_HTML;
    }

    @PostMapping(UrlPaths.STEM)
    public String stem(@RequestParam int prosjektid, @RequestParam int arrangementid, @RequestParam String epost, @RequestParam int verdi) {

        ProsjektBean prosjekt = prosjektService.getProsjektById(prosjektid);

        // Sjekke om prosjekt eksisterer
        if (prosjekt == null) {
            return UrlPaths.ERRORPAGE;
        }

        // Sjekke om prosjektet deltar i arragementet
        ArrangementdeltagelseBean deltagelse = prosjekt.getArragementdeltagelser()
                .stream()
                .filter(a -> a.getArrangement().getArrangementid() == arrangementid)
                .findAny()
                .orElse(null);

        // PRosjektet deltar ikke i arrengementet
        if (deltagelse == null) {
            return UrlPaths.ERRORPAGE;
        }

        // TODO: bruke arragemetntdeltagelse
        stemmeService.addStemme(new StemmeBean(deltagelse, epost, validateVerdi(verdi)));

        return "redirect:" + UrlPaths.STEM +"?navn=" + prosjekt.getProsjektnavn();
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
