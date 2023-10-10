package com.example.esalab2.entity;

import com.example.esalab2.service.OrderAuditListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@EntityListeners(OrderAuditListener.class)
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "number")
    private String number;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order1")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<OrderItem> content;
}
