package no.hvl.dat109.prosjekt;

import no.hvl.dat109.spring.beans.ProsjektBean;

import java.io.File;

public class FileHandler {

    /**
     * Remove project files for a single project
     *
     * @param prosjekt project to remove
     */
    public static void removeProject(ProsjektBean prosjekt) {
        //Find path to project folder
        File projectFolder = new File(Paths.PROJECT_PATH + prosjekt.getProsjektnavn());

        //If it is a directory then purge it
        if (projectFolder.isDirectory()) {
            purgeFolder(projectFolder);
            //Delete the folder itself when all others are gone
            boolean done = projectFolder.delete();
            System.out.printf("Prosjekt mappe: %s ble slettet: %s\n", prosjekt.getProsjektnavn(), done);
        } else {
            //If it is not working then print error
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

    /**
     * Delete all project folders from disk
     */
    public static void removeAllProjects() {
        File projectFolder = new File(Paths.PROJECT_PATH);
        if (projectFolder.isDirectory())
            purgeFolder(projectFolder);
    }
}