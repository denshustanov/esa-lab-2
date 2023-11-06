package com.example.esalab2.jms.consumer;

import com.example.esalab2.entity.Audit;
import com.example.esalab2.service.AuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.example.esalab2.configuration.JmsConfiguration.DESTINATION;

@Component
@Slf4j
public class JmsAuditConsumer {
    private final AuditService auditService;


    public JmsAuditConsumer(AuditService auditService) {
        this.auditService = auditService;
    }


    @JmsListener(destination = DESTINATION)
    public void handleMessage(Audit audit) {
        log.info(String.format("Received %s audit event for entity %s", audit.getAction(), audit.getEntity()));
        auditService.create(audit);
    }


}
