package no.hvl.dat109.spring.controller;

import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProsjektController {

    //Todo make useful controller methods

    @Autowired
    private IProsjektService prosjektService;

    @GetMapping("/prosjekt/{id}")
    String getProsjektById(@PathVariable("id") int id, Model model) {

        ProsjektBean prosjekt = prosjektService.getProsjektById(id);

        if (prosjekt == null) {
            return "error";
        }

        model.addAttribute("prosjekt", prosjekt);

        return "prosjekt";

    }

    @GetMapping("/prosjekt/add")
    String addProsjekt(@RequestParam int id, @RequestParam String navn) {
        ProsjektBean prosjekt = new ProsjektBean();

        prosjekt.setProsjektid(id);
        prosjekt.setProsjektnavn(navn);

        prosjektService.addProsjekt(prosjekt);

        return "redirect:/prosjekt/" + id;
    }
}
