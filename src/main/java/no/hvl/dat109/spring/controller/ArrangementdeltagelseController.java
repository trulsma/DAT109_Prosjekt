package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.utilities.UrlPaths;
import no.hvl.dat109.spring.beans.ArrangementBean;
import no.hvl.dat109.spring.beans.ArrangementdeltagelseBean;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.beans.UsersBean;
import no.hvl.dat109.spring.service.Interfaces.IArrangementService;
import no.hvl.dat109.spring.service.Interfaces.IArrangementdeltagelseService;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

import static no.hvl.dat109.prosjekt.handlers.Processing.*;

@Controller
public class ArrangementdeltagelseController {

    @Autowired
    private IArrangementdeltagelseService deltagelseService;

    @Autowired
    private IArrangementService arrangementService;

    @Autowired
    private IProsjektService prosjektService;

    @GetMapping(UrlPaths.ARRANGEMENT_WITH_ID)
    public String getArrangementDeltagelser(@PathVariable("id") int id, Model model, HttpSession session) {
        ArrangementBean arrangement = arrangementService.getArrangement(id);
        List<ProsjektBean> deltagelser = deltagelseService.getAllProsjektFromArrangement(arrangement);

        UsersBean user = (UsersBean) session.getAttribute("user");

        model.addAttribute("level", user == null ? 3 : user.getUsergroupLevel());
        model.addAttribute("deltagelser", deltagelser);
        return UrlPaths.ARRANGEMNT_DELTAGELSE_HTML;
    }

    @PostMapping(UrlPaths.ARRANGEMENT_DELTA)
    String deltaArrangement(@RequestParam("arrangementid") int arrangementid,
                            @RequestParam("prosjektid") int prosjektid) {

        //Finn prosjekt og arrangement
        ProsjektBean prosjekt = prosjektService.getProsjektById(prosjektid);
        ArrangementBean arrangement = arrangementService.getArrangement(arrangementid);

        setQrLink(prosjekt, arrangement);

        //Legg deltagelsen til databasen
        deltagelseService.addArrangementDeltagelse(arrangement, prosjekt);


        return "redirect:" + UrlPaths.DASHBOARD + "/" + prosjektid;
    }

    /**
     * Sets the QR link to the database
     *
     * @param prosjekt prosjekt to set the qr link to
     */
    private void setQrLink(ProsjektBean prosjekt, ArrangementBean arrangement) {
        if (arrangement.getStemmemetode().getMetodeparameter() > 1)
            prosjekt.setShortenedurl(generateShortlink(prosjekt, arrangement));
        else
            prosjekt.setShortenedurl(generateStemmeLink(prosjekt, arrangement));

        prosjekt.setQrimagepath(getRelativeProjectQRCode(prosjekt, arrangement));
        prosjektService.updateProsjekt(prosjekt);
    }
}
