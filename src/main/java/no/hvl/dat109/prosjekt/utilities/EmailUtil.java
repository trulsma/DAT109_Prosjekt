package no.hvl.dat109.prosjekt.utilities;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class EmailUtil {

    public static void sendEmail(String toEmail, String body) {
        try {
            String from = "elprosjekto@hotmail.com";
            String pass = "Gruppe3DAT109";

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp-mail.outlook.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(from, pass);
                        }
                    });


            //Create message and add recipient
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from, "NoReply"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, "Bruker"));

            //Set the subject
            msg.setSubject("Stand innloggings data");

            //set the mail text
            msg.setText(body);

            //Send mail
            Transport.send(msg);
            System.out.println("Email sent successfully...");
        } catch (UnsupportedEncodingException | MessagingException e) {
            System.out.println(e);
        }
    }
}
