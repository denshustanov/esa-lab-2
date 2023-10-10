package com.example.esalab2.service.impl;

import com.example.esalab2.entity.Audit;
import com.example.esalab2.respository.AuditRepository;
import com.example.esalab2.service.AuditService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {
    private final AuditRepository auditRepository;

    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public Audit create(Audit audit) {
        return auditRepository.save(audit);
    }

    @Override
    public List<Audit> findAll() {
        return auditRepository.findAll();
    }
}
