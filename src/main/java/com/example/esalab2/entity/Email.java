package com.example.esalab2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "email")
@Getter
@Setter
public class Email {
    @Id
    @Column(name = "id")
    @GeneratedValue
    UUID id;

    @Column(name = "recipient")
    String recipient;

    @Column(name = "subject")
    String subject;

    @Column(name = "body")
    String body;

}

