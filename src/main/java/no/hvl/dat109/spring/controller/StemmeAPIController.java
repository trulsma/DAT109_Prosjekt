package no.hvl.dat109.spring.controller;

public class StemmeAPIController {

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
}
