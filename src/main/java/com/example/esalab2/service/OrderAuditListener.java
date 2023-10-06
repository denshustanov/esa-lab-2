package com.example.esalab2.service;

import com.example.esalab2.entity.Audit;
import com.example.esalab2.entity.Order;
import com.example.esalab2.jms.JmsPublisher;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.time.LocalDateTime;

@Slf4j
public class OrderListener {
    public static final String PERSIST = "PERSIST";
    public static final String UPDATE = "UPDATE";

    public static final String REMOVE = "REMOVE";

    private static AuditorAware<String> auditorAware;
    private static JmsPublisher publisher;

    @Autowired
    public void init(AuditorAware<String> auditorAware, JmsPublisher publisher){
        OrderListener.auditorAware = auditorAware;
        OrderListener.publisher = publisher;
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
        audit.setAuditor(auditorAware.getCurrentAuditor().orElse(null));

        publisher.publish(audit);
    }
}
