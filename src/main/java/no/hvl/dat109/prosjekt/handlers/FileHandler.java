package no.hvl.dat109.prosjekt.handlers;

import no.hvl.dat109.prosjekt.utilities.ProsjektPaths;
import no.hvl.dat109.spring.beans.ProsjektBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static no.hvl.dat109.prosjekt.handlers.Processing.qrImageFileName;

public class FileHandler {

    /**
     * Remove project files for a single project
     *
     * @param prosjekt project to remove
     */
    public static void removeProject(ProsjektBean prosjekt) {
        //Find path to project folder
        File projectFolder = new File(ProsjektPaths.PROJECT_PATH + prosjekt.getProsjektnavn());

        //If it is a directory then purge it
        if (projectFolder.isDirectory()) {
            purgeFolder(projectFolder);
            //Delete the folder itself when all others are gone
            boolean done = projectFolder.delete();
            System.out.printf("Prosjekt mappe: %s ble slettet: %s\n", prosjekt.getProsjektnavn(), done);
        } else {
            //If it is not working then print ERRORPAGE
            System.err.println("Error removing project. Folder is probably missing");
        }
    }

    /**
     * Metod to remove all content from a folder
     *
     * @param projectFolder folder to check
     */
    private static void purgeFolder(File projectFolder) {
        //Create an array of all files in project
        File[] files = projectFolder.listFiles();

        //Check if folder is null and return if that is the case
        if (files == null) return;

        //Loop through all files and delete them
        for (File file : files) {
            //Is a file a directory, then purge that folder aswell
            if (file.isDirectory())
                purgeFolder(file);

            if (file.delete())
                System.out.println(file.getName() + " Has been deleted.");

        }
    }

    public static void removeProjectQrCode(ProsjektBean prosjektBean) {
        File file = new File(ProsjektPaths.PROJECT_PATH + qrImageFileName(prosjektBean));
        if (file.delete()) System.out.println("Managed to delete QR code");
        else System.out.println("Qr code does not exist!");
    }

    /**
     * Delete all project folders from disk
     */
    public static void removeAllProjects() {
        File projectFolder = new File(ProsjektPaths.PROJECT_PATH);
        if (projectFolder.isDirectory())
            purgeFolder(projectFolder);
    }

    public static String createLogoImage(MultipartFile logo, ProsjektBean prosjekt) {
        String output = ProsjektPaths.PROJECT_PATH + prosjekt.getProsjektnavn() + "/images/logo_" +
                logo.getOriginalFilename().replaceAll(" ", "_");
        createPicture(logo, output);
        return output;
    }

    public static String createBackgroundImage(MultipartFile logo, ProsjektBean prosjekt) {
        String output = ProsjektPaths.PROJECT_PATH + prosjekt.getProsjektnavn() + "/images/background_" +
                logo.getOriginalFilename().replaceAll(" ", "_");
        createPicture(logo, output);
        return output;
    }

    private static void createPicture(MultipartFile file, String outputPath) {
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(outputPath);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}