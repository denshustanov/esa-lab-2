package com.example.esalab2.service.impl;

import com.example.esalab2.entity.Audit;
import com.example.esalab2.respository.AuditRepository;
import com.example.esalab2.service.AuditService;
import com.example.esalab2.service.EmailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {
    public static final String EMAIL_NOTIFICATION_SUBJECT = "Audit failing";

    private final AuditRepository auditRepository;
    private final EmailService emailService;

    public AuditServiceImpl(AuditRepository auditRepository, EmailService emailService) {
        this.auditRepository = auditRepository;
        this.emailService = emailService;
    }

    @Override
    public Audit create(Audit audit) {
        audit = auditRepository.save(audit);
        emailService.sendEmail(
                "admin@gmail.com",
                EMAIL_NOTIFICATION_SUBJECT,
                String.format("Changes to entity %s were added. Audit id: %s.", audit.getEntity(), audit.getId())
        );

        return audit;
    }

    @Override
    public List<Audit> findAll() {
        return auditRepository.findAll();
    }
}
