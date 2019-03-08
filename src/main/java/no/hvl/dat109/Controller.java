package no.hvl.dat109;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import no.hvl.dat109.service.IBedriftService;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    IBedriftService service;

    @GetMapping("/test")
    public String show(Model model) {
        model.addAttribute("test", service.test());
        return "index";
    }
}
