package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.utilities.UrlPaths;
import no.hvl.dat109.spring.beans.UsersBean;
import no.hvl.dat109.spring.service.Interfaces.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class DiverseController {

    @Autowired
    private IUsersService usersService;

    @PostMapping("/registrer_deg")
    String registerBruker(@RequestParam String redirect_url, @RequestParam String epost, HttpSession session) {
        session.setAttribute("epost", epost);

        usersService.createVoterUser(epost);
        return "redirect:" + redirect_url;
    }

    @PostMapping("/admin_login")
    String adminLogin(@RequestParam String redirect_url, @RequestParam String passord, HttpSession session) {
        session.setAttribute("passord", passord);

        if (passord.equals("hemmelig")) {
            session.setAttribute("passord", passord);
        } else {
            return "redirect:admin_login?redirect_url=" + redirect_url;
        }

        return "redirect:" + redirect_url;
    }

    @GetMapping(UrlPaths.INDEX)
    String loggedInIndex(HttpSession session) {

        //Get user from session
        UsersBean user = (UsersBean) session.getAttribute("user");

        if (user == null || user.getUsergroupLevel() == 3)
            return UrlPaths.INDEX_HTML; //If user is not logged in og just a voter
        else if (user.getUserGroup().getGrouplevel() == 2)
            return UrlPaths.LOGGED_IN_HTML; //if you are a stand user
        else
            return UrlPaths.ADMIN_INDEX; //if you are admin
    }

    @GetMapping(UrlPaths.BASE_KONTAKT)
    String getKontakt() {
        return UrlPaths.KONTAKT_HTML;
    }

    @GetMapping("/admin_login")
    String getAdminLogin(@RequestParam(required = false) String redirect_url, Model model) {

        if (redirect_url == null) {
            redirect_url = "/adminindex";
        }
        model.addAttribute("redirect_url", redirect_url);
        return "adminpages/admin_login.html";
    }

    @GetMapping("/adminindex")
    String getAdminIndex() {
        return "adminpages/adminindex.html";
    }


    @GetMapping("/registrer_deg")
    String getRegistrerBruker(@RequestParam(required = false) String redirect_url, Model model) {
        if (redirect_url == null) {
            redirect_url = "/";
        }
        model.addAttribute("redirect_url", redirect_url);
        return "registrer_deg";
    }

    @GetMapping("/index")
    String getIndex() {
        return "index";
    }


    @GetMapping("/dashboard")
    String getDashboard() {
        return "adminpages/statistikk/dashboard";
    }
}


