package com.example.emailservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String receiverEmail, String subject, String message, byte[] fileData) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        // Enable multipart mode for the email
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(receiverEmail);
        helper.setSubject(subject);
        helper.setText(message);

        // Attach the PDF file
        helper.addAttachment("GreetingCard", new ByteArrayResource(fileData));

        javaMailSender.send(mimeMessage);
    }

}
