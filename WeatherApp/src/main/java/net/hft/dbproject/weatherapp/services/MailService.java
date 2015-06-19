/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hft.dbproject.weatherapp.services;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Jan
 */
public class MailService {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Session session;

    private String emailFrom;

    /**
     *
     */
    public MailService() {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.web.de");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.user", "weatherapp@web.de");
        properties.put("mail.smtp.password", "password");
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.starttls.enable", "true");

        emailFrom = "weatherapp@web.de";

        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("weatherapp@web.de", "password");
            }
        };

        session = Session.getInstance(properties, authenticator);
    }

    /**
     *
     * @param to
     * @param subject
     * @param text
     * @return
     */
    public Boolean sendMail(String to, String subject, String text) {

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject, "utf-8");
            message.setContent(text, "text/html; charset=utf-8");
            message.setSentDate(new Date());
            message.saveChanges();

            Transport.send(message);

        } catch (MessagingException ex) {
            System.out.println(ex.toString());
            return false;
        }
        return true;
    }

    public static String getMailMessage(String userName, String location) {
        return "Dear Appuser,\n\n"
                + "you received this mail because \n\t" + userName + " was looking for \n\t" + location + "\n\n Regards, Weahterappteam";
    }

}
