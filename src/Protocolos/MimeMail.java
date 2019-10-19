/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Protocolos;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import utils.Constantes;

/**
 *
 * @author ADL
 */
public class MimeMail {
  
    public void sendHtmlEmail(String toAddress,
            String subject, String message) throws AddressException,
            MessagingException {

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", Constantes.MAIL_SERVER_HOST);
        properties.put("mail.smtp.port", 25);
        properties.put("mail.smtp.auth", "false");
        properties.put("mail.smtp.starttls.enable", "true");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Constantes.MAIL_USER, Constantes.MAIL_PASSWORD);
            }
        };

        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        try {
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(Constantes.MAIL_USER + "@virtual.fcet.uagrm.edu.bo"));
            InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            // set plain text message
            msg.setContent(message, "text/html");
            //System.out.println("Envie MAIL: to=" + toAddress + " subject=" + subject + " data:" + message);
            // sends the e-mail
            Transport.send(msg);
        } catch (MessagingException mex) {
            mex.printStackTrace();
            System.out.println("Error");
        }
    }    
}
