package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.utilities.UrlPaths;
import no.hvl.dat109.spring.beans.*;
import no.hvl.dat109.spring.service.Interfaces.IArrangementService;
import no.hvl.dat109.spring.service.Interfaces.IArrangementdeltagelseService;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StemmeAPIController {

    @Autowired
    IProsjektService prosjektService;

    @Autowired
    IArrangementService arrangementService;

    @GetMapping("/api/prosjekt/{prosjektid}/arrangement/{arrangementid}")
    ResponseEntity<?> getStemmerForProsjekt(@PathVariable("prosjektid") int prosjektid,
                                            @PathVariable("arrangementid") int arrangementid,
                                            @RequestParam(required = false) Integer steps) {
        ProsjektBean prosjekt = prosjektService.getProsjektById(prosjektid);

        if (prosjekt == null) {
            return ResponseEntity.notFound().build();
        }

        // Sjekke om prosjektet deltar i arragementet
        ArrangementdeltagelseBean deltagelse = prosjekt.getArragementdeltagelser()
                .stream()
                .filter(a -> a.getArrangement().getArrangementid() == arrangementid)
                .findAny()
                .orElse(null);

        if (deltagelse == null) {
           return ResponseEntity.notFound().build();
        }



        List<AnonymStemmeBean> stemmer = deltagelse.getStemmer().stream().map(AnonymStemmeBean::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(stemmer);
    }


    @GetMapping("/api/prosjekter/{arrangementid}")
    ResponseEntity<?> getStemmerForAlleProsjekt(@PathVariable("arrangementid") int arrangementid,
                                                @RequestParam(required = false) String order,
                                                @RequestParam(required = false) Integer limit) {

        ArrangementBean arrangement = arrangementService.getArrangement(arrangementid);

        if (arrangement == null) {
            return ResponseEntity.notFound().build();
        }

        List<ProsjektMedStemmerBean> prosjekterMedStemmer = arrangement.getDeltagelser().stream()
                .map(a -> { return new ProsjektMedStemmerBean(
                            a.getProsjekt().getProsjektid(),
                            a.getProsjekt().getProsjektnavn(),
                            a.getProsjekt().getProsjektbeskrivelse(),
                            a.getStemmer().size(),
                           0);
                }).collect(Collectors.toList());

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
    }
}
