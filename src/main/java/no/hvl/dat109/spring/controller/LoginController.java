package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.utilities.UrlPaths;
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
    private IUserGroupService groupService;

    @GetMapping(UrlPaths.LOGIN)
    String loginPage() {
        //Login for voters
        return UrlPaths.LOGIN_HTML;
    }

    @PostMapping(UrlPaths.LOGIN)
    String loginUser(@RequestParam String epost, HttpSession session) {



        return UrlPaths.INDEX_HTML;
    }

    @GetMapping(UrlPaths.USER_LOGIN)
    String userLogin() {
        //Login for stand users and admin
        return UrlPaths.USER_LOGIN_HTML;
    }
}
