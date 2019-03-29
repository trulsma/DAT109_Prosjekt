package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.utilities.UrlPaths;
import no.hvl.dat109.spring.beans.*;
import no.hvl.dat109.spring.service.Interfaces.IArrangementService;
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

    @Autowired
    private IArrangementService arrangementService;

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
        stemmeService.addStemme(new StemmeBean(deltagelse, epost, validateVerdi(verdi, deltagelse.getArrangement())));

        return "redirect:" + UrlPaths.STEM + "?navn=" + prosjekt.getProsjektnavn();
    }

    @GetMapping(UrlPaths.STEM_FROM_LINK)
    String stemFromUrl(@PathVariable("pid") int pid, @PathVariable("aid") int aid, HttpSession session, Model model) {

        UsersBean user = (UsersBean) session.getAttribute("user");

        //TODO FIKS REDIRECT RETT
        if (user == null) return "redirect:" + UrlPaths.REGISTRER_DEG;
        ProsjektBean prosjekt = prosjektService.getProsjektById(pid);
        ArrangementBean arrangement = arrangementService.getArrangement(aid);

        if (prosjekt == null || arrangement == null) return "redirect:" + UrlPaths.INDEX;

        // Sjekke om prosjektet deltar i arragementet
        ArrangementdeltagelseBean deltagelse = prosjekt.getArragementdeltagelser()
                .stream()
                .filter(a -> a.getArrangement().getArrangementid() == aid)
                .findAny()
                .orElse(null);

        // PRosjektet deltar ikke i arrengementet
        if (deltagelse == null) {
            return UrlPaths.ERRORPAGE;
        }

        stemmeService.addStemme(new StemmeBean(deltagelse, user.getUsername(), 1));
        model.addAttribute("navn", prosjekt.getProsjektnavn());
        return "redirect:" + UrlPaths.STEM;
    }

    /**
     * Validere at verdien ikke kan overstige arrangementet sin stemmeverdi og at det kan ikke bli mindre enn 1
     *
     * @param verdi       verdien av stemmen
     * @param arrangement arrangementet for å finne rangeringen
     * @return stemmeverdien
     */
    private int validateVerdi(int verdi, ArrangementBean arrangement) {
        int metodeparameter = arrangement.getStemmemetode().getMetodeparameter();
        if (verdi <= 0) verdi = 1;
        else if (verdi > metodeparameter) verdi = metodeparameter;
        return verdi;
    }

}
