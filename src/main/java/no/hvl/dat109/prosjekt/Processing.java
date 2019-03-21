package no.hvl.dat109.prosjekt;

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
import java.nio.file.Paths;

import static com.rosaloves.bitlyj.Bitly.as;
import static com.rosaloves.bitlyj.Bitly.shorten;

public class Processing {

    public Processing() {
    }

    private static final String HOST = "http://192.168.0.25:8080/";
    private static final String IMAGEPATH = "src/main/resources/static/projects/";
    private static final String PROJECTPATH = "projects/";
    private static final int QRCODE_SIZE = 400;

    /**
     * Generer en bit.ly link og lag QR kode bilde
     *
     * @param prosjekt prosjektet du vil lage link til
     * @return bit.ly link
     */
    public static String generateShortlink(ProsjektBean prosjekt) {
        String shortenedLink = createQRCodeLink(prosjekt.getProsjektid());
        createImageInResources(prosjekt, shortenedLink);
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
    private static void createImageInResources(ProsjektBean prosjekt, String shortenedLink) {
        //Pathen til resource mappen
        String dir = IMAGEPATH + prosjekt.getProsjektnavn() + "/images/";
        File directory = new File(dir);

        //If directory exists then we can create, or try to make directory
        boolean canCreateFile = directory.exists() || directory.mkdirs();

        //If operation over succeeded then we can create the image
        if (canCreateFile) {
            //Create file where output should be
            File outputfile = new File(dir + getImagePath(prosjekt));
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
    public static String createQRCodeLink(int prosjektid) {
        Url url = as("elprosjekto", "R_eea8a14a9ffe422e8ca79f8b26aabe8a")
                .call(shorten(HOST + "prosjekt/" + prosjektid));
        return url.getShortUrl();
    }

    /**
     * Metode for å finne bildene igjen
     *
     * @param prosjekt prosjektet du vil finne bilder til
     * @return en path til qr bildet
     */
    public static String getImagePath(ProsjektBean prosjekt) {
        return prosjekt.getProsjektid()
                + "_" + prosjekt.getProsjektnavn().replaceAll(" ", "_") + ".png";
    }

    public static String getProjectImagePath(ProsjektBean prosjektBean) {
        return PROJECTPATH + prosjektBean.getProsjektnavn() + "/images/" + getImagePath(prosjektBean);
    }
}
