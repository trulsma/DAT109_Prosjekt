package no.hvl.dat109.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class DiverseController {

    @PostMapping("/registrer_deg")
    String registerBruker(@RequestParam String redirect_url, @RequestParam String epost, HttpSession session) {
        session.setAttribute("epost", epost);

        return "redirect:" + redirect_url;
    }

    @PostMapping("/admin_login")
    String adminLogin(@RequestParam String redirect_url, @RequestParam String passord, HttpSession session){
        session.setAttribute("passord", passord);

        if (passord.equals("hemmelig")){
            session.setAttribute("passord", passord);
        }else {
            return "redirect:admin_login?redirect_url=" + redirect_url;
        }

        return "redirect:" + redirect_url;
    }

    @GetMapping("/admin_login")
    String getAdminLogin(@RequestParam(required = false) String redirect_url, Model model){

        if (redirect_url == null){
            redirect_url = "adminpages/adminindex.html";
        }
        model.addAttribute("redirect_url", redirect_url);
        return "adminpages/admin_login.html";
    }

    @GetMapping("/adminindex")
    String getAdminIndex(@RequestParam String redirect_url, Model model){
        model.addAttribute("redirect_url", redirect_url);
        return "adminpages/adminindex.html";
    }


    @GetMapping("/registrer_deg")
    String getRegistrerBruker(@RequestParam String redirect_url, Model model) {
        model.addAttribute("redirect_url", redirect_url);
        return "registrer_deg";
    }

    @GetMapping("/index")
    String getIndex() {
        return "index";
    }
}


