package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.utilities.UrlPaths;
import no.hvl.dat109.spring.beans.BedriftBean;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.service.Interfaces.IBedriftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BedriftController {

    //Todo make useful controller methods

    @Autowired
    private IBedriftService repository;

    @GetMapping(UrlPaths.BEDRIFT_WITH_ID)
    public String getBedriftById(@PathVariable("id") int id, Model model) {
        BedriftBean bedrift = repository.getBedriftById(id);
        List<ProsjektBean> prosjektBeanList = bedrift.getProsjekter();

        model.addAttribute("bedrift", bedrift);
        model.addAttribute("partof", prosjektBeanList.size());
        return UrlPaths.BEDRIFT_HTML;
    }

    @GetMapping(UrlPaths.ALLE_BEDRIFTER)
    String getAlleBedrifter(Model model) {
        model.addAttribute("bedrifter", repository.getAlleBedrifter());

        return UrlPaths.ALLE_BEDRIFTER_HTML;
    }

    @GetMapping(UrlPaths.ADD_BEDRIFT)
    String addBedrift() {
        return UrlPaths.REGISTRER_BEDRIFT_HTML;
    }

    @PostMapping(UrlPaths.ADD_BEDRIFT)
    String addBedriftPostRequest(
            @RequestParam String bedriftnavn,
            @RequestParam String bedriftbeskrivelse) {

        BedriftBean bedrift = new BedriftBean(bedriftnavn, bedriftbeskrivelse);
        repository.addBedrift(bedrift);

        return "redirect:" + UrlPaths.BEDRIFT_BASE + "/" + bedrift.getBedriftid();
    }


}
