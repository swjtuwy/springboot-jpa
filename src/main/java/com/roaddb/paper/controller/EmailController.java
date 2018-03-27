package com.roaddb.paper.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.roaddb.paper.model.Paper;
import com.roaddb.paper.model.User;
import com.roaddb.paper.repository.PaperRepository;
import com.roaddb.paper.repository.UserRepository;

@Component
public class EmailController {

    @Value("${paper.emailfrom}")
    private String emailFrom;

    @Value("${paper.smtphost}")
    private String smtpServerHost;

    @Value("${paper.smtpport}")
    private String smtpServerPort;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PaperRepository paperRepository;

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    /**
     * send email.
     */
    public void sendResultByEmail(Long paperId, Long userId) {

        Paper paper = paperRepository.findOne(paperId);
        User user = userRepository.findOne(userId);

        Set<User> userSet = paper.getSubUsers();

        logger.info("+sendResultByEmail+");

        String emailSubject = String.format("the %s paper modify by % ", paper.getTitle(), user.getUserName());
        String emailText = "Hi,\r\n \r\n The Serialize data processing is successful.";

        if (!CollectionUtils.isEmpty(userSet)) {
            for (User innerUser : userSet) {
                sendResultByEmailInner(innerUser.getEmail(), emailSubject, emailText);
            }
        }
    }

    protected void sendResultByEmailInner(String email, String subject, String text) {
        boolean isOk = sendEmail(email, subject, text);
        if (isOk) {
            logger.info("send email success");
        } else {
            logger.info("send email failed");
        }
    }

    private boolean sendEmail(String email, String subject, String text) {
        boolean flag = false;
        String to = email;
        String from = emailFrom;
        String host = smtpServerHost;
        String port = smtpServerPort;
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            // message.addRecipient(Message.RecipientType.TO, new
            // InternetAddress(to));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(text);

            // Send message
            Transport.send(message);
            logger.info("Sent message successfully....");
            flag = true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

        return flag;
    }

}
