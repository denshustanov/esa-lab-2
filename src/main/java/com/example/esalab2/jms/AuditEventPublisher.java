package com.example.esalab2.jms;

import com.example.esalab2.entity.Audit;

public interface AuditEventPublisher {
    void publish(Audit audit);
}
