package no.hvl.dat109.prosjekt.handlers;

import no.hvl.dat109.prosjekt.utilities.ProsjektPaths;
import no.hvl.dat109.spring.beans.ArrangementBean;
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
        File projectFolder = new File(ProsjektPaths.FILE_UPLOAD_PATH + prosjekt.getProsjektnavn());

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

    public static void removeProjectQrCode(ProsjektBean prosjektBean, ArrangementBean arrangement) {
        File file = new File(ProsjektPaths.RELATIVE_UPLOAD_PATH + "arrangement/" + arrangement.getArrangementid() + "/" + qrImageFileName(prosjektBean));
        if (file.delete()) System.out.println("Managed to delete QR code");
        else System.out.println("Qr code does not exist!");
    }

    /**
     * Delete all project folders from disk
     */
    public static void removeAllProjects() {
        File projectFolder = new File(ProsjektPaths.FILE_UPLOAD_PATH);
        if (projectFolder.isDirectory())
            purgeFolder(projectFolder);
    }

    public static String createLogoImage(MultipartFile logo, ProsjektBean prosjekt, ArrangementBean arrangement) {
        String output = ProsjektPaths.FILE_UPLOAD_PATH + prosjekt.getProsjektnavn() + "/arrangementer/" + arrangement.getArrangementid() + "/images/";
        createPicture(logo, output, "logo.png");
        return output;
    }

    public static String createBackgroundImage(MultipartFile logo, ProsjektBean prosjekt, ArrangementBean arrangement) {
        String output = ProsjektPaths.FILE_UPLOAD_PATH + prosjekt.getProsjektnavn() + "/arrangementer/" + arrangement.getArrangementid() + "/images/";
        createPicture(logo, output, "background.png");
        return output;
    }

    private static void createPicture(MultipartFile file, String outputPath, String filename) {
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            File folder = new File(outputPath);
            if (!folder.exists())
                System.out.println(folder.mkdirs() ? "Created folder" : "Something went wrong when creating folder");
            Path path = Paths.get(outputPath + filename);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}