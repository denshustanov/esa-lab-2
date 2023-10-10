package com.example.esalab2.service;

import com.example.esalab2.entity.Audit;
import com.example.esalab2.entity.Order;
import com.example.esalab2.jms.AuditEventPublisher;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.time.LocalDateTime;

@Slf4j
public class OrderAuditListener {
    public static final String PERSIST = "PERSIST";
    public static final String UPDATE = "UPDATE";

    public static final String REMOVE = "REMOVE";

    //    @Autowired
    private static AuditorAware<String> auditorAware;
    private static AuditEventPublisher publisher;

    @Autowired
    public void init(AuditorAware<String> auditorAware, AuditEventPublisher publisher) {
        OrderAuditListener.auditorAware = auditorAware;
        OrderAuditListener.publisher = publisher;
    }

    @PostPersist
    private void onAfterPersist(Order order){
        handleUpdate(order, PERSIST);
    }

    @PostUpdate
    private void onAfterUpdate(Order order){
        handleUpdate(order, UPDATE);
    }

    @PostRemove
    private void onAfterRemove(Order order){
        handleUpdate(order, REMOVE);
    }


    private void handleUpdate(Order order, String action){
        Audit audit = new Audit();
        audit.setAction(action);
        audit.setTimestamp(LocalDateTime.now());
        audit.setRecordId(order.getId());
        audit.setEntity(Order.class.getName());
        audit.setAuditor(auditorAware.getCurrentAuditor().orElse(null));
        publisher.publish(audit);
    }
}
