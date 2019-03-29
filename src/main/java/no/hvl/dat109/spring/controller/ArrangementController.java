package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.utilities.UrlPaths;
import no.hvl.dat109.prosjekt.utilities.Utilities;
import no.hvl.dat109.spring.beans.StemmeMetodeBean;
import no.hvl.dat109.spring.service.Interfaces.IArrangementService;
import no.hvl.dat109.spring.service.Interfaces.IStemmeMetodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ArrangementController {

    @Autowired
    private IArrangementService arrangementService;

    @Autowired
    private IStemmeMetodeService stemmeMetodeService;

    @GetMapping(UrlPaths.ARRANGEMENT_BASE)
    public String test() {

        System.out.println(arrangementService.getAllArrangementAsString());

        return UrlPaths.INDEX_HTML;
    }

    @PostMapping(UrlPaths.ADD_ARRANGEMENT)
    String addArrangement(@RequestParam("arrangementnavn") String arrangementnavn,
                          @RequestParam("arrangementbeskrivelse") String arrangementbeskrivelse,
                          @RequestParam("arrangementdate") String arrangementdate,
                          @RequestParam("stemmemetode") int stemmemetode) {

        StemmeMetodeBean stemmeMetodeBean = stemmeMetodeService.getStemmeMetode(stemmemetode);

        arrangementService.addArrangement(arrangementnavn,
                arrangementbeskrivelse,
                stemmeMetodeBean,
                Utilities.formatDate(arrangementdate));

        return "redirect:" + UrlPaths.DASHBOARD;
    }
}
