package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.Processing;
import no.hvl.dat109.spring.beans.BedriftBean;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.service.Interfaces.IBedriftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BedriftController {

    //Todo make useful controller methods

    @Autowired
    private IBedriftService repository;

    @GetMapping("/bedrift/{id}")
    public String getBedriftById(@PathVariable("id") int id, Model model) {
        BedriftBean bedrift = repository.getBedriftById(id);
        List<ProsjektBean> prosjektBeanList = bedrift.getProsjekter();

        model.addAttribute("bedrift", bedrift);
        model.addAttribute("partof", prosjektBeanList.size());
        return "bedrift";
    }

    /*@GetMapping("/bedrift/add/{navn}/{beskrivelse}")
    public String addBedrift(@PathVariable("navn") String navn, @PathVariable("beskrivelse") String beskrivelse, Model model) {

        //If for some reason database is empty again, you can easily add new one for debugging

        if (repository.exists(navn)) return "redirect:/bedrifter.html";
        repository.addBedrift(new navn, beskrivelse);
        model.addAttribute("test", repository.getAll());

        //example of redirect
        return "redirect:/bedrifter.html";
    }*/
    @GetMapping("/bedrifter")
    String getAlleBedrifter(Model model) {
        model.addAttribute("bedrifter", repository.getAlleBedrifter());

        return "bedrifter.html";
    }

    @GetMapping("/bedrift/add")
    String addBedrift() {
        return "registrer_bedrift";
    }

    @PostMapping("/bedrift/add")
    String addBedriftPostRequest(
            @RequestParam String bedriftnavn,
            @RequestParam String bedriftbeskrivelse) {

        BedriftBean bedrift = new BedriftBean(bedriftnavn, bedriftbeskrivelse);
        repository.addBedrift(bedrift);

        return "redirect:/bedrift/" + bedrift.getBedriftid();
    }


}
