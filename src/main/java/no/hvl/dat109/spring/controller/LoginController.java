package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.utilities.UrlPaths;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.beans.UsersBean;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import no.hvl.dat109.spring.service.Interfaces.IUserGroupService;
import no.hvl.dat109.spring.service.Interfaces.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private IUsersService usersService;

    @Autowired
    private IProsjektService prosjektService;

    @Autowired
    private IUserGroupService groupService;

    @Autowired

    @GetMapping(UrlPaths.LOGIN)
    String loginPage() {
        //Login for voters
        return UrlPaths.LOGIN_HTML;
    }

    @PostMapping(UrlPaths.LOGIN)
    String loginUser(@RequestParam String epost, HttpSession session) {

        //Create user from the posted epost
        UsersBean user = usersService.getUserByName(epost);
        if (user == null) return "redirect:" + UrlPaths.LOGIN_HTML; //TODO CREATE ERROR WHEN USER NOT FOUND

        //Add the user to session and go back to index page
        session.setAttribute("user", user);
        return "redirect:" + UrlPaths.INDEX_HTML;
    }

    @GetMapping(UrlPaths.USER_LOGIN)
    String userLogin() {
        //Login for stand users and admin
        return UrlPaths.USER_LOGIN_HTML;
    }

    @PostMapping(UrlPaths.USER_LOGIN)
    String postUserLogin(@RequestParam String epost, @RequestParam String passord, HttpSession session, Model model) {

        //Find user from post and return if not valid
        UsersBean user = usersService.validUser(epost, passord);
        if (user == null) return "redirect:" + UrlPaths.USER_LOGIN; //TODO CRETE ERROR WHEN NOT FOUND

        //Find project with current user as owner, if any
        ProsjektBean prosjekt = prosjektService.getProsjektFromOwner(user);
        if (prosjekt != null) session.setAttribute("prosjekt", prosjekt);

        //Add user to session
        session.setAttribute("user", user);

        return "redirect:" + UrlPaths.INDEX;
    }
}
