package com.example.esalab2.service;

import com.example.esalab2.entity.Audit;

import java.util.List;

public interface AuditService {
    Audit create(Audit audit);

    List<Audit> findAll();
}
