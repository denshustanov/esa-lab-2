package com.example.esalab2.respository;

import com.example.esalab2.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuditRepository extends JpaRepository<Audit, UUID> {
}
