package com.example.esalab2.jms;

import com.example.esalab2.entity.Audit;
import com.example.esalab2.service.AuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JmsAuditConsumer {
    public static final String DESTINATION = "audited-action";
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
