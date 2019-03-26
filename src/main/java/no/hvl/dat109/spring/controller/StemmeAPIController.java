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
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class StemmeAPIController {

    private static long MINUTT = 60 * 1000;

    @Autowired
    IProsjektService prosjektService;

    @Autowired
    IArrangementService arrangementService;

    // TODO: flytte maping til urlMapping filen.

    /**
     * @param prosjektid, prosjektet som stemmene skal hentes for
     * @param arrangementid, arrangementet som stemmene skal hentes for
     * @param stepSize, hvor store intervalet skal være, i minutter, default er 30 min
     * @return en liste resultater i formen av ResultatStemmeBeans
     */
    @GetMapping("/api/prosjekt/{prosjektid}/arrangement/{arrangementid}")
    ResponseEntity<?> getStemmerForProsjekt(@PathVariable("prosjektid") int prosjektid,
                                            @PathVariable("arrangementid") int arrangementid,
                                            @RequestParam(required = false) Integer stepSize) {
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

        List<StemmeBean> stemmer = deltagelse.getStemmer().stream()
                .sorted(Comparator.comparing(StemmeBean::getStemmetidspunkt))
                .collect(Collectors.toList());

        if (stepSize == null || stepSize <= 0) {
            stepSize = 30;
        }

        if (stemmer.size() < 1) {
            return ResponseEntity.ok().body(new ResultatStemmeBean(0.0, 0, prosjektid, new Date()));
        }

        // Finne første tid og runde av til.
        long start = stemmer.get(0).getStemmetidspunkt().getTime();
        start = start - (start % (MINUTT * stepSize));

        long end = stemmer.get(stemmer.size() - 1).getStemmetidspunkt().getTime();

        List<ResultatStemmeBean> resultat = new ArrayList<>();

        for (; start < end; start += stepSize * MINUTT) {
            long time = start;
            double average = stemmer.stream().filter(a -> a.getStemmetidspunkt().getTime() < time).mapToDouble(StemmeBean::getStemmeverdi).average().orElse(0.0);
            int antall = (int)stemmer.stream().filter(a -> a.getStemmetidspunkt().getTime() < time).count();

            resultat.add(new ResultatStemmeBean(average, antall, prosjektid, new Date(time)));
        }

        double average = stemmer.stream().mapToDouble(StemmeBean::getStemmeverdi).average().orElse(0.0);
        int antall = (int)stemmer.size();

        resultat.add(new ResultatStemmeBean(average, antall, prosjektid, new Date(end)));

        return ResponseEntity.ok().body(resultat);
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
