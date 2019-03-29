package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.utilities.UrlPaths;
import no.hvl.dat109.spring.beans.UsersBean;
import no.hvl.dat109.spring.service.Interfaces.IUsersService;
import no.hvl.dat109.spring.service.UserGroupService;
import no.hvl.dat109.spring.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private IUsersService usersService;

    @GetMapping(UrlPaths.LOGIN + "/{id}")
    public String logIn(@PathVariable("id") int id, HttpSession session) {

        //TODO: MIDLERTIDIG INNLOGGIN FOR SJEKKING

        UsersBean user = usersService.getUserById(id);
        if (user == null)
            return "redirect:" + UrlPaths.INDEX;

        session.setAttribute("user", user);
        return "redirect:" + UrlPaths.DASHBOARD;
    }

    @GetMapping(UrlPaths.LOGOUT)
    public String logOut(HttpSession session) {
        if (session.getAttribute("user") != null)
            session.removeAttribute("user");
        return "redirect:" + UrlPaths.INDEX;
    }
}
