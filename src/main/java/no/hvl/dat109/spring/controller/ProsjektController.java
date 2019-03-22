package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.EmailUtil;
import no.hvl.dat109.prosjekt.FileHandler;
import no.hvl.dat109.spring.beans.*;
import no.hvl.dat109.spring.service.Interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.Iterator;

import static no.hvl.dat109.prosjekt.FileHandler.removeProjectQrCode;
import static no.hvl.dat109.prosjekt.Processing.generateShortlink;
import static no.hvl.dat109.prosjekt.Processing.getProjectImagePath;

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

    @GetMapping("/prosjekter")
    String getAlleProsjekter(Model model) {
        model.addAttribute("prosjekter", prosjektService.getAlleProsjekter());

        return "adminpages/prosjekter.html";
    }

    @GetMapping("/prosjekt/{id}/qr")
    String getProsjektQR(@PathVariable("id") int id, Model model) {

        ProsjektBean prosjekt = prosjektService.getProsjektById(id);

        if (prosjekt == null) {
            return "error";
        }

        model.addAttribute("prosjekt", prosjekt);

        return "standpages/qrkode";
    }

    @GetMapping("/prosjekt/{id}/qr/create")
    String createProsjektQR(@PathVariable("id") int id) {

        ProsjektBean prosjekt = prosjektService.getProsjektById(id);

        if (prosjekt == null) {
            return "error";
        }

        //Denne koden kjører av en eller annen merkelig grunn når jeg faktisk bare skriver linken inn i nettleser #spooky
        removeProjectQrCode(prosjekt);
        setQrLink(prosjekt);

        // OBS! serveren kan redirecte før qrkoden bildet er lagret og vil ikke være oppdattert uten er refresh
        return "redirect:/prosjekt/" + id + "/qr";
    }

    @GetMapping("/prosjekt/{id}")
    String getProsjektById(@PathVariable("id") int id, Model model, HttpSession session) {

        if (session.getAttribute("epost") == null) {
            return "redirect:/registrer_deg?redirect_url=" + "/prosjekt/" + id;
        }

        ProsjektBean prosjekt = prosjektService.getProsjektById(id);
        UsersBean user = (UsersBean) session.getAttribute("user");

        if (prosjekt == null) {
            return "error";
        }

        // Guess who's back, back again
        // Bedrift boyy is back, tell a friend
        //System.out.println(prosjekt.getSammarbeidsbedrift() + " THIS IS BEDRIFT BOYYY🔥");

        model.addAttribute("samarbeidspartner", prosjekt.getSammarbeidsbedrift());
        model.addAttribute("prosjekt", prosjekt);

        return "userpages/stand";
    }

    @GetMapping("/prosjekt/add")
    String addProsjekt(Model model) {
        model.addAttribute("kategorier", kategoriService.getAllKategorier());
        model.addAttribute("bedrifter", bedriftService.getAlleBedrifter());
        return "adminpages/registrering/registrer_prosjekt";
    }

    @PostMapping("/prosjekt/add")
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
        UsersBean user = userService.createNewUser(email);

        //Lag prosjektet med alt vi har fått så langt
        ProsjektBean prosjekt = new ProsjektBean(prosjektnavn, prosjektbeskrivelse, bedrift, studie, user);
        prosjektService.addProsjekt(prosjekt);

        //Send email with password
        sendEmail(email, user);

        //Etter prosjektet er laget kan kan vi danne qr bilde link
        setQrLink(prosjekt);

        session.setAttribute("epost", email);
        session.setAttribute("user", user);

        //TODO SEND PASSORD TIL USER PÅ EMAIL

        return "redirect:/prosjekt/" + prosjekt.getProsjektid();
    }

    @GetMapping("/prosjekt/{id}/remove")
    String removeProject(@PathVariable("id") int id, HttpSession session) {
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
        return "redirect:/index";
    }

    @GetMapping("/prosjekter/apocalypse")
    String removeAllProsjektFiles() {
        FileHandler.removeAllProjects();
        return "index";
    }

    /**
     * Send email with login info for stand user
     *
     * @param email email of user
     * @param user  user with username and password
     */
    private void sendEmail(String email, UsersBean user) {
        String messagebody = String.format("Username: %s\nPassword: %s", user.getUsername(), user.getPassword());
        Thread thread = new Thread(() -> EmailUtil.sendEmail(email, messagebody));
        thread.start();
    }

    /**
     * Sets the QR link to the database
     *
     * @param prosjekt prosjekt to set the qr link to
     */
    private void setQrLink(ProsjektBean prosjekt) {
        prosjekt.setShortenedurl(generateShortlink(prosjekt));
        prosjekt.setQrimagepath(getProjectImagePath(prosjekt));
        prosjektService.updateProsjekt(prosjekt);
    }
}