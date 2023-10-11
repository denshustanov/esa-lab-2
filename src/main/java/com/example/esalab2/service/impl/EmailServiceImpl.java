package com.example.esalab2.service.impl;

import com.example.esalab2.entity.Email;
import com.example.esalab2.respository.EmailRepository;
import com.example.esalab2.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;


    public EmailServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }


    @Override
    public void sendEmail(String recipient, String subject, String body) {
        Email email = new Email();
        email.setBody(body);
        email.setRecipient(recipient);
        email.setSubject(subject);
        emailRepository.save(email);
    }
}
