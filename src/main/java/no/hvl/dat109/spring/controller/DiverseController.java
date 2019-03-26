package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.utilities.UrlPaths;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.beans.UsersBean;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import no.hvl.dat109.spring.service.Interfaces.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DiverseController {

    @Autowired
    private IUsersService usersService;

    @Autowired
    private IProsjektService prosjektService;

    @PostMapping(UrlPaths.REGISTRER_DEG)
    String registerBruker(@RequestParam String redirect_url, @RequestParam String epost, HttpSession session, HttpServletRequest request) {
        session.setAttribute("epost", epost);
        String ipaddress = request.getRemoteAddr();
        usersService.createVoterUser(epost, ipaddress);
        return "redirect:" + redirect_url;
    }

    @GetMapping(UrlPaths.INDEX)
    String loggedInIndex(HttpSession session, Model model) {

        //Get user from session
        UsersBean user = (UsersBean) session.getAttribute("user");
        model.addAttribute("user", user);

        //If you are a voter or dont have a user
        if (user == null || user.getUsergroupLevel() == 3) {
            model.addAttribute("prosjektid", 0);
            return UrlPaths.INDEX_HTML; //If user is not logged in og just a voter
        } else {
            //If you are admin or
            ProsjektBean prosjekt = prosjektService.getProsjektFromOwner(user);
            model.addAttribute("prosjektid", prosjekt == null ? 0 : prosjekt.getProsjektid());
            return UrlPaths.LOGGED_IN_HTML;
        }
    }

    @GetMapping(UrlPaths.BASE_KONTAKT)
    String getKontakt() {
        return UrlPaths.KONTAKT_HTML;
    }


    @GetMapping(UrlPaths.REGISTRER_DEG)
    String getRegistrerBruker(@RequestParam(required = false) String redirect_url, Model model) {
        if (redirect_url == null) {
            redirect_url = UrlPaths.INDEX;
        }
        model.addAttribute("redirect_url", redirect_url);
        return UrlPaths.REGISTRER_DEG_HTML;
    }

    @GetMapping("/index")
    String getIndex() {

        return "redirect:" + UrlPaths.INDEX;
    }

    @GetMapping("/")
    String getDefaultIndex(Model model) {

        model.addAttribute("prosjekter", prosjektService.getAlleProsjekter());
        return "redirect:" + UrlPaths.INDEX;
    }


    @GetMapping(UrlPaths.DASHBOARD)
    String getDashboard() {
        return UrlPaths.DASHBOARD_ADMIN_HTML;
    }
}


