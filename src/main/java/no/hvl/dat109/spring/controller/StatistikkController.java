package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.utilities.UrlPaths;
import no.hvl.dat109.spring.beans.ArrangementBean;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.service.Interfaces.IArrangementService;
import no.hvl.dat109.spring.service.Interfaces.IBedriftService;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StatistikkController {

    @Autowired
    private IProsjektService prosjektService;

    @Autowired
    private IBedriftService bedriftService;

    @Autowired
    private IArrangementService arrangementService;

    //nødvending å gjøre tingene med Model?
    @GetMapping(UrlPaths.BASE_STATISTIKK)
    public String getStatistics() {
        return UrlPaths.ADMIN_STATISTIKK_HTML;
    }


    @GetMapping(UrlPaths.PROSJEKT_STATISTIKK)
    String getProsjektStatistikk(@PathVariable("id") int id, Model model) {
        ProsjektBean prosjekt = prosjektService.getProsjektById(id);

        if (prosjekt == null) {
            return UrlPaths.ERRORPAGE;
        }

        model.addAttribute("id", prosjekt.getProsjektid());

        return UrlPaths.STAND_STATISTIKK_HTML;
    }

    @GetMapping(UrlPaths.PROSJEKT_DASHBOARD)
    String getDashboardForProsjekt(@PathVariable("id") int id, Model model) {

        //This means the owner is not logged in to the project
        if (id == 0) {
            return "redirect:" + UrlPaths.INDEX;
        }

        ProsjektBean prosjekt = prosjektService.getProsjektById(id);
        if (prosjekt == null) {
            return UrlPaths.ERRORPAGE;
        }

        model.addAttribute("prosjekt", prosjekt);

        return UrlPaths.STAND_DASHBOARD_HTML;
    }

    @GetMapping(UrlPaths.PROSJEKT_ARRANGEMENT_DASHBOARD)
    String getDashboardForProsjektAndArrangement(@PathVariable("id") int id, @PathVariable("arrangementid") int arrangementid, Model model) {

        //This means the owner is not logged in to the project
        if (id == 0) {
            return "redirect:" + UrlPaths.INDEX;
        }

        ProsjektBean prosjekt = prosjektService.getProsjektById(id);
        if (prosjekt == null) {
            return UrlPaths.ERRORPAGE;
        }

        ArrangementBean arrangement = arrangementService.getArrangement(arrangementid);
        if (arrangement == null) {
            return UrlPaths.ERRORPAGE;
        }

        model.addAttribute("prosjekt", prosjekt);
        model.addAttribute("arrangement", arrangement);

        return UrlPaths.STAND_ARRANGEMENT_DASHBOARD_HTML;
    }
}
