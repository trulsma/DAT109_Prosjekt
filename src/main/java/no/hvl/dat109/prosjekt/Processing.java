package no.hvl.dat109.prosjekt;

import no.hvl.dat109.spring.beans.BedriftBean;
import no.hvl.dat109.spring.beans.ProsjektBean;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

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

    public String createQRCode(ProsjektBean prosjekt) {

        BufferedImage image = null;
        try {
            // TODO: bytte ut urlen med riktig url for prosjektet
            image = ImageIO.read(new URL("https://i.imgur.com/sMyNvNk.png"));
            String dir = "src/main/resources/static/images/";
            File outputfile = new File(dir + prosjekt.getProsjektid() + "_" + prosjekt.getProsjektnavn().replaceAll(" ", "_") + ".png");
            ImageIO.write(image, "png", outputfile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
