package lk.ijse.channelingCenter.controller;

import com.google.protobuf.Message;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.Authenticator;
import java.sql.Date;
import java.util.Properties;

public class EmailFromController {

//
////    public mail(){
////        initialize();
////    }
//
//    public void sendMail() {
//
//        String from = "";
//        String host = "localhost";
//        String port = "2525";
//
//        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
//
//        Properties props = System.getProperties();
//        props.setProperty("mail.smtp.host", "smpt.gmail.com");
//        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
//        props.setProperty("mail.smtp.socketFactory.fallback", "false");
//        props.setProperty("mail.smtp.port", "465");
//        props.setProperty("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.debug", "true");
//        props.put("mail.store.protocol", "pop3");
//        props.put("mail.transport.protocol", "smtp");
//        final String username = "";
//        final String password = "";
//        try{
//            Session session = Session.getDefaultInstance(props,
//                    new Authenticator() {
//                        protected PasswordAuthentication getPasswordAuthentication() {
//                            return new PasswordAuthentication(username, password.toCharArray());
//                        }
//                    });
//            Message msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress(from));
//            msg.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(txtEmail.getText(), false));
//            msg.setSubject(txtSub.getText());
//            msg.setText("How are you");
//            msg.setSentDate(new Date(2003));
//            Transport.send(msg);
//            System.out.println("Message sent.");
//        } catch (MessagingException e) {
//            System.out.println("Erreur d'envoi, cause: " + e);
//        }
//
//    }




}