package no.hvl.dat109.prosjekt.handlers;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.rosaloves.bitlyj.Url;
import no.hvl.dat109.spring.beans.ProsjektBean;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.rosaloves.bitlyj.Bitly.as;
import static com.rosaloves.bitlyj.Bitly.shorten;
import static no.hvl.dat109.prosjekt.utilities.ProsjektPaths.*;

public class Processing {
    private static final int QRCODE_SIZE = 400;

    /**
     * Generer en bit.ly link og lag QR kode bilde
     *
     * @param prosjekt prosjektet du vil lage link til
     * @return bit.ly link
     */
    public static String generateShortlink(ProsjektBean prosjekt) {
        String shortenedLink = generateShortBitlyLinkForQR(prosjekt.getProsjektid());
        createQRImage(prosjekt, shortenedLink);
        return shortenedLink;
    }

    /**
     * Metode for å generere QRCode bilder
     *
     * @param projectlink linken koden skal til
     * @return bilde av QR koden
     * @throws WriterException om det er problemer med å skrive til bilde
     */
    private static BufferedImage generateQRCodeImage(String projectlink) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(projectlink, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    /**
     * Metode for å lagre qr koden i resources mappen
     *
     * @param prosjekt      prosjekt
     * @param shortenedLink bit.ly linken
     */
    private static void createQRImage(ProsjektBean prosjekt, String shortenedLink) {
        //Pathen til resource mappen
        String dir = RELATIVE_PROJECT_PATH + prosjekt.getProsjektnavn() + "/images/";
        File directory = new File(dir);

        //If directory exists then we can create, or try to make directory
        boolean canCreateFile = directory.exists() || directory.mkdirs();

        //If operation over succeeded then we can create the image
        if (canCreateFile) {
            //Create file where output should be
            File outputfile = new File(dir + qrImageFileName(prosjekt));
            BufferedImage image = null;
            try {
                //Generate the QRCodeImage and write it to output
                image = generateQRCodeImage(shortenedLink);
                ImageIO.write(image, "png", outputfile);
                //Check if it actually created the image
                System.out.println("Did it create the image? " + outputfile.exists());
            } catch (WriterException | IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Couldnt either create directory or it doesnt exist");
        }
    }

    /**
     * Generer en bit.ly link
     *
     * @param prosjektid prosjektid for linken
     * @return bit.ly link
     */
    public static String generateShortBitlyLinkForQR(int prosjektid) {
        System.out.println(HOST + "prosjekt/" + prosjektid + " URL");
        Url url = as("elprosjekto", "R_eea8a14a9ffe422e8ca79f8b26aabe8a")
                .call(shorten(HOST + "prosjekt/" + prosjektid));
        return url.getShortUrl();
    }

    /**
     * Metode for å finne bildene igjen
     * Ex: 1_Prosjekt_navn.png
     *
     * @param prosjekt prosjektet du vil finne bilder til
     * @return en path til qr bildet
     */
    public static String qrImageFileName(ProsjektBean prosjekt) {
        return prosjekt.getProsjektid()
                + "_" + prosjekt.getProsjektnavn().replaceAll(" ", "_") + ".png";
    }

    /**
     * Get full image path for the Project.
     * Ex: src/main/resources/static/ + project name
     *
     * @param prosjektBean prosjekt å finne qr bilde til
     * @return path til QR image
     */
    public static String getFullQRImagePath(ProsjektBean prosjektBean) {
        return PROJECT_PATH + prosjektBean.getProsjektnavn() + "/images/" + qrImageFileName(prosjektBean);
    }

    /**
     * Get relative path to images from a project
     * Ex: projects/Prosjekt navn/images/
     *
     * @param prosjektBean project to get imagepath to
     * @return relative imagepath often used in database
     */
    public static String getRelativeProjectImagePath(ProsjektBean prosjektBean) {
        return RELATIVE_IMAGE_PATH + prosjektBean.getProsjektnavn() + "/images/";
    }
}
