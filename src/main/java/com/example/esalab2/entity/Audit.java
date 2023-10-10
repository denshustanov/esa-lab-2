package com.example.esalab2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit")
@Getter
@Setter
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "entity")
    String entity;

    @Column(name = "record_id")
    UUID recordId;

    @Column(name = "action")
    String action;

    @Column(name = "auditor")
    String auditor;

    @Column(name = "timestamp")
    LocalDateTime timestamp;
}
