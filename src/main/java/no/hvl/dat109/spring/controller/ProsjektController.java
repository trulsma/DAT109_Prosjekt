package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.handlers.Processing;
import no.hvl.dat109.prosjekt.utilities.EmailUtil;
import no.hvl.dat109.prosjekt.handlers.FileHandler;
import no.hvl.dat109.prosjekt.utilities.ProsjektPaths;
import no.hvl.dat109.prosjekt.utilities.UrlPaths;
import no.hvl.dat109.prosjekt.utilities.Utilities;
import no.hvl.dat109.spring.beans.*;
import no.hvl.dat109.spring.service.Interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import static no.hvl.dat109.prosjekt.handlers.FileHandler.removeProjectQrCode;
import static no.hvl.dat109.prosjekt.handlers.Processing.*;

@Controller
public class ProsjektController {

    //Todo make useful controller methods

    @Autowired
    private IProsjektService prosjektService;

    @Autowired
    private IArrangementService arrangementService;

    @Autowired
    private IBedriftService bedriftService;

    @Autowired
    private IKategoriService kategoriService;

    @Autowired
    private IStudieService studieService;

    @Autowired
    private IUsersService userService;

    @GetMapping(UrlPaths.ALLE_PROSJEKTER)
    String getAlleProsjekter(Model model) {
        model.addAttribute("prosjekter", prosjektService.getAlleProsjekter());

        return UrlPaths.ALLE_PROSJEKTER_HTML;
    }

    @GetMapping(UrlPaths.SHOW_QR)
    String getProsjektQR(@PathVariable("id") int id, Model model) {

        ProsjektBean prosjekt = prosjektService.getProsjektById(id);

        if (prosjekt == null) {
            return UrlPaths.ERRORPAGE;
        }

        model.addAttribute("prosjekt", prosjekt);

        return UrlPaths.STAND_QR_HTML;
    }

    @GetMapping(UrlPaths.CREATE_QR_IMAGE)
    String createProsjektQR(@PathVariable("id") int id, @PathVariable("arrangementid") int arrangementid) {

        ProsjektBean prosjekt = prosjektService.getProsjektById(id);
        ArrangementBean arrangement = arrangementService.getArrangement(arrangementid);

        if (prosjekt == null) {
            return UrlPaths.ERRORPAGE;
        }

        //Denne koden kjører av en eller annen merkelig grunn når jeg faktisk bare skriver linken inn i nettleser #spooky
        removeProjectQrCode(prosjekt, arrangement);
        setQrLink(prosjekt, arrangement);

        // OBS! serveren kan redirecte før qrkoden bildet er lagret og vil ikke være oppdattert uten er refresh
        return "redirect:" + UrlPaths.BASE_PROSJEKT + "/" + id + "/arrangement/" + arrangementid + "/qr";
    }

    @GetMapping(UrlPaths.PROSJEKT_WITH_ID)
    String getProsjektById(@PathVariable("id") int id, Model model) {
        ProsjektBean prosjekt = prosjektService.getProsjektById(id);

        if (prosjekt == null) {
            return UrlPaths.ERRORPAGE;
        }

        model.addAttribute("prosjekt", prosjekt);
        if (prosjekt.getArragementdeltagelser() != null) {
            model.addAttribute("deltagelser", prosjekt.getArragementdeltagelser());
        } else {
            model.addAttribute("deltagelser", new ArrayList<ArrangementdeltagelseBean>());
        }

        return UrlPaths.STAND_DELTAGELSE_HTML;
    }


    @GetMapping(UrlPaths.PROSJEKT_WITH_ID_AND_ARRANGEMENT)
    String getProsjektByIdAndByArrangement(@PathVariable("id") int id,
                                           @PathVariable("arrangementid") int arrangementid,
                                           Model model,
                                           HttpSession session) {

        if (session.getAttribute("epost") == null) {
            return "redirect:" + UrlPaths.REGISTRER_DEG + "?redirect_url=prosjekt/" + id + "/arrangement/" + arrangementid;
        }

        ProsjektBean prosjekt = prosjektService.getProsjektById(id);
        ArrangementBean arrangement = arrangementService.getArrangement(arrangementid);
        UsersBean user = (UsersBean) session.getAttribute("user");

        if (prosjekt == null) {
            return UrlPaths.ERRORPAGE;
        }

        // Sjekke om prosjektet deltar i arragementet
        ArrangementdeltagelseBean deltagelse = prosjekt.getArragementdeltagelser()
                .stream()
                .filter(a -> a.getArrangement().getArrangementid() == arrangementid)
                .findAny()
                .orElse(null);

        if (deltagelse == null) {
            return UrlPaths.ERRORPAGE;
        }

        String relativeProjectQR = Processing.getRelativeProjectQRCode(prosjekt, arrangement);

        model.addAttribute("samarbeidspartner", prosjekt.getSammarbeidsbedrift());
        model.addAttribute("qrpath", relativeProjectQR);
        model.addAttribute("shorturl", Processing.decodeQRCode(relativeProjectQR));
        model.addAttribute("prosjekt", prosjekt);
        model.addAttribute("arrangement", deltagelse.getArrangement());

        return UrlPaths.STAND_HTML;
    }

    @GetMapping(UrlPaths.ADD_PROSJEKT)
    String addProsjekt(Model model) {
        model.addAttribute("kategorier", kategoriService.getAllKategorier());
        model.addAttribute("bedrifter", bedriftService.getAlleBedrifter());
        return UrlPaths.REGISTRER_PROSJEKT_HTML;
    }

    @PostMapping(UrlPaths.ADD_PROSJEKT)
    String addProsjektPostRequest(
            @RequestParam String prosjektnavn,
            @RequestParam String prosjektbeskrivelse,
            @RequestParam int samarbeidspartner,
            @RequestParam int institutt,
            @RequestParam String email,
            HttpSession session) {
        //  System.out.println(id);

        //Finn en bedrift fra id-en til comboboxen
        BedriftBean bedrift = bedriftService.getBedriftById(samarbeidspartner);

        //Finn studie fra box
        StudieBean studie = studieService.getStudieById(institutt);

        //Lag en user med emailen vi fikk
        String password = Utilities.generateShortPassword(4);
        UsersBean user = userService.createNewUser(email, password);

        //Lag prosjektet med alt vi har fått så langt
        ProsjektBean prosjekt = new ProsjektBean(prosjektnavn, prosjektbeskrivelse, bedrift, studie, user);
        prosjektService.addProsjekt(prosjekt);

        //Send email with password
        sendEmail(email, user, password);

        //Etter prosjektet er laget kan kan vi danne qr bilde link

        session.setAttribute("epost", email);
        session.setAttribute("user", user);

        return "redirect:" + UrlPaths.BASE_PROSJEKT + "/" + prosjekt.getProsjektid();
    }

    @PostMapping(UrlPaths.PROSJEKT_ENDRE_NAVN)
    String editName(@PathVariable("id") int id, @RequestParam String prosjektnavn) {
        //TODO SJEKK OM NAVN ENDRINGEN BLIR ENDRET AV EIGER / ADMIN
        ProsjektBean prosjekt = prosjektService.getProsjektById(id);
        prosjektService.changeNameOfProject(prosjekt, prosjektnavn);
        return "redirect:" + UrlPaths.PROSJEKT_DASHBOARD;
    }

    @PostMapping(UrlPaths.PROSJEKT_ENDRE_BESKRIVELSE)
    String endreBeskrivelse(@PathVariable("id") int id, @RequestParam String beskrivelse) {
        //TODO SJEKK OM BESKRIVELSEN BLIR ENDRET AV EIGER / ADMIN
        ProsjektBean prosjekt = prosjektService.getProsjektById(id);
        prosjektService.changeBeskrivelse(prosjekt, beskrivelse);
        return "redirect:" + UrlPaths.PROSJEKT_DASHBOARD;
    }

    @PostMapping(UrlPaths.REMOVE_PROSJEKT)
    String removeProject(@PathVariable("id") int id, HttpSession session) {
        //TODO SJEKK OM PROSJEKTET BLIR SLETTET AV EIGER / ADMIN
        //Finn prosjektet
        ProsjektBean prosjekt = prosjektService.getProsjektById(id);
        //Slett alle prosjektfiler
        FileHandler.removeProject(prosjekt);

        //Fjern prosjektet og fjern useren fra databasen
        prosjektService.removeProject(prosjekt);
        userService.removeUser(prosjekt.getProsjektEiger());

        //Fjern session attributter
        session.removeAttribute("user");
        session.removeAttribute("epost");
        return "redirect:" + UrlPaths.INDEX;
    }

    @GetMapping(UrlPaths.REMOVE_ALL_PROSJEKT_FILES)
    String removeAllProsjektFiles() {
        //TODO SJEKK OM ADMIN UTFØRER OPERASJONEN
        FileHandler.removeAllProjects();
        return "redirect:" + UrlPaths.INDEX;
    }

    /**
     * Send email with login info for STAND_HTML user
     *
     * @param email email of user
     * @param user  user with username and password
     */
    private void sendEmail(String email, UsersBean user, String password) {
        String messagebody = String.format("Username: %s\nPassword: %s", user.getUsername(), password);
        Thread thread = new Thread(() -> EmailUtil.sendEmail(email, messagebody));
        thread.start();
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