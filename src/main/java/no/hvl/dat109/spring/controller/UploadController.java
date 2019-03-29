package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.utilities.ProsjektPaths;
import no.hvl.dat109.prosjekt.utilities.UrlPaths;
import no.hvl.dat109.spring.beans.ArrangementBean;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.service.Interfaces.IArrangementService;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

    @Autowired
    private IProsjektService prosjektService;

    @Autowired
    private IArrangementService arrangementService;

    @GetMapping(UrlPaths.UPLOAD_PROSJEKT_IMAGES)
    public String index(@PathVariable("id") int id, @PathVariable("aid") int aid, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("aid", aid);
        return UrlPaths.UPLOAD_HTML;
    }

    @PostMapping(UrlPaths.UPLOAD_PROSJEKT_IMAGES) // //new annotation since 4.3
    public String singleFileUpload(@RequestParam(value = "background", required = false) MultipartFile background,
                                   @RequestParam(value = "logo", required = false) MultipartFile logo,
                                   @PathVariable("id") int id,
                                   @PathVariable("aid") int aid) {

        String backgroundPath = "", logoPath = "";

        ProsjektBean prosjekt = prosjektService.getProsjektById(id);
        ArrangementBean arrangementBean = arrangementService.getArrangement(aid);

        if (!background.isEmpty())
            backgroundPath = prosjektService.createBackground(background, prosjekt, arrangementBean);

        if (!logo.isEmpty())
            logoPath = prosjektService.createLogo(logo, prosjekt, arrangementBean);


        if (!logoPath.equals(""))
            prosjektService.updatePicturePath(prosjekt, logoPath, arrangementBean);
        if (!backgroundPath.equals(""))
            prosjektService.updateBackgroundPath(prosjekt, backgroundPath, arrangementBean);

        return "redirect:" + UrlPaths.BASE_PROSJEKT + "/" + id + "/arrangement/" + aid;
    }


}
