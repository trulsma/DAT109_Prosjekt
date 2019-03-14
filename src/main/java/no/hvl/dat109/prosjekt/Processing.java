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
import java.nio.file.FileSystems;
import java.nio.file.Path;

import static com.rosaloves.bitlyj.Bitly.as;
import static com.rosaloves.bitlyj.Bitly.shorten;

public class Processing {

    /*
    //https://goo.gl/kUQ6fX
    public Processing(BedriftBean bean) {
        this.bean = bean;

        JFrame frame = new JFrame(bean.getBedriftnavn());

        BufferedImage image = null;
        try {
            image = ImageIO.read(new URL("https://i.imgur.com/sMyNvNk.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        JLabel information = new JLabel(
                "<html><body>" +
                        "<h1>" + bean.getBedriftnavn() + "</h1>" +
                        "<h3>" + bean.getBedriftbeskrivelse() + "</h3>" +
                        "<p>Scan QRkoden!</p></body></html>"
        );

        information.setHorizontalAlignment(SwingConstants.CENTER);

        frame.setLayout(new GridLayout(2, 1));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(information);
        frame.add(imageLabel);

        frame.setVisible(true);
        frame.pack();
    }
    */
    public Processing() {

    }

    private static final String HOST = "http://www.localhost:8080/";
    private static final int QRCODE_SIZE = 400;

    public String generateShortlink(ProsjektBean prosjekt) {
        String shortenedLink = createQRCodeLink(prosjekt.getProsjektid());
        createImage(prosjekt, shortenedLink);
        return shortenedLink;
    }

    private static BufferedImage generateQRCodeImage(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    private void createImage(ProsjektBean prosjekt, String shortenedLink) {
        String dir = "src/main/resources/static/images/";
        File outputfile = new File(dir + prosjekt.getProsjektid() + "_" + prosjekt.getProsjektnavn().replaceAll(" ", "_") + ".png");
        BufferedImage image = null;
        try {
            image = generateQRCodeImage(shortenedLink);
            ImageIO.write(image, "png", outputfile);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }


    public static String createQRCodeLink(int prosjektid) {
        Url url = as("elprosjekto", "R_eea8a14a9ffe422e8ca79f8b26aabe8a")
                .call(shorten(HOST + "prosjekt/" + prosjektid));
        return url.getShortUrl();
    }
}
