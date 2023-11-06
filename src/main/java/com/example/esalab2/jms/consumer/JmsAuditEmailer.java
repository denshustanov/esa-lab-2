package com.example.esalab2.jms.consumer;

import com.example.esalab2.entity.Audit;
import com.example.esalab2.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.example.esalab2.configuration.JmsConfiguration.DESTINATION;

@Component
@Slf4j
public class JmsAuditEmailer {
    public static final String EMAIL_NOTIFICATION_SUBJECT = "Audit failing";

    private final EmailService emailService;

    public JmsAuditEmailer(EmailService emailService) {
        this.emailService = emailService;
    }

    @JmsListener(destination = DESTINATION)
    public void handleMessage(Audit audit) {
        log.info(String.format("Received %s audit event for entity %s", audit.getAction(), audit.getEntity()));
        emailService.sendEmail(
                "admin@gmail.com",
                EMAIL_NOTIFICATION_SUBJECT,
                String.format("Changes to entity %s were added. Audit id: %s.", audit.getEntity(), audit.getId())
        );
    }
}
