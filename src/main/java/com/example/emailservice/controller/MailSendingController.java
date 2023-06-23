package com.example.emailservice.controller;

import com.example.emailservice.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class MailSendingController {
    @Autowired
    private MailService mailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestParam("to") String to,
                                            @RequestParam("subject") String subject,
                                            @RequestParam("message") String message,
                                            @RequestParam("fileData") byte[] fileData) {

        try {
            mailService.sendEmail(to, subject, message, fileData);
            return new ResponseEntity<>("Successfully send the Email.", OK);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
