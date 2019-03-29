package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.utilities.UrlPaths;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.beans.UsersBean;
import no.hvl.dat109.spring.service.Interfaces.*;
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
    private IKategoriService kategoriService;

    @Autowired
    private IBedriftService bedriftService;

    @Autowired
    private IUsersService usersService;

    @Autowired
    private IProsjektService prosjektService;

    @Autowired
    private IStemmeMetodeService stemmeMetode;


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
        model.addAttribute("prosjekter", prosjektService.getAlleProsjekter());

        //If you are a voter or dont have a user
        if (user == null || user.getUsergroupLevel() == 3) {
            model.addAttribute("prosjektid", 0);
            model.addAttribute("level", 3);
        } else {
            //If you are admin or
            ProsjektBean prosjekt = prosjektService.getProsjektFromOwner(user);
            model.addAttribute("level", user.getUsergroupLevel());
            model.addAttribute("prosjektid", prosjekt == null ? 0 : prosjekt.getProsjektid());
        }
        return UrlPaths.INDEX_HTML;
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

    @GetMapping(UrlPaths.DASHBOARD)
    String getDashboard(HttpSession session, Model model) {

        UsersBean user = (UsersBean) session.getAttribute("user");
        if (user == null || user.getUsergroupLevel() > 1)
            return "redirect:" + UrlPaths.INDEX;

        model.addAttribute("kategorier", kategoriService.getAllKategorier());
        model.addAttribute("bedrifter", bedriftService.getAlleBedrifter());
        model.addAttribute("stemmemetoder", stemmeMetode.getAllStemmemetoder());
        return UrlPaths.DASHBOARD_ADMIN_HTML;
    }


}


