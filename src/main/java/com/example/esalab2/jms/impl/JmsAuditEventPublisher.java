package com.example.esalab2.jms.impl;

import com.example.esalab2.entity.Audit;
import com.example.esalab2.jms.AuditEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static com.example.esalab2.configuration.JmsConfiguration.DESTINATION;

@Component
@Slf4j
public class JmsAuditEventPublisher implements AuditEventPublisher {
    private final JmsTemplate jmsTemplate;

    public JmsAuditEventPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void publish(Audit audit) {
        log.info("Publishing audit event");
        jmsTemplate.convertAndSend(DESTINATION, audit);
    }
}
