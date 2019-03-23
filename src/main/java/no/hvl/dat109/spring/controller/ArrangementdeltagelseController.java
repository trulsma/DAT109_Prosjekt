package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.utilities.UrlPaths;
import no.hvl.dat109.spring.beans.ArrangementBean;
import no.hvl.dat109.spring.beans.ArrangementdeltagelseBean;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.service.Interfaces.IArrangementService;
import no.hvl.dat109.spring.service.Interfaces.IArrangementdeltagelseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ArrangementdeltagelseController {

    @Autowired
    private IArrangementdeltagelseService deltagelseService;

    @Autowired
    private IArrangementService arrangementService;

    @GetMapping(UrlPaths.ARRANGEMENT_WITH_ID)
    public String getArrangementDeltagelser(@PathVariable("id") int id, Model model) {
        ArrangementBean arrangement = arrangementService.getArrangement(id);
        System.out.println("Arrangement: " + arrangement);
        List<ProsjektBean> deltagelser = deltagelseService.getAllProsjektFromArrangement(arrangement);
        model.addAttribute("deltagelser", deltagelser);
        return UrlPaths.ARRANGEMNT_DELTAGELSE_HTML;
    }
}
