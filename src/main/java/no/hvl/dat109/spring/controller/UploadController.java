package no.hvl.dat109.spring.controller;

import no.hvl.dat109.prosjekt.Processing;
import no.hvl.dat109.prosjekt.ProsjektPaths;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

    @Autowired
    private IProsjektService prosjektService;
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = ProsjektPaths.MAIN_PATH;

    @GetMapping("/prosjekt/{id}/upload")
    public String index(@PathVariable("id") int id, Model model) {
        ProsjektBean prosjekt = prosjektService.getProsjektById(id);
        model.addAttribute("id", prosjekt.getProsjektid());
        return "upload";
    }

    @PostMapping("prosjekt/{id}/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable("id") int id) {

        try {
            ProsjektBean prosjekt = prosjektService.getProsjektById(id);
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(ProsjektPaths.PROJECT_PATH + prosjekt.getProsjektnavn() +
                    "/images/" + file.getOriginalFilename().replaceAll(" ", "_"));
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

}
