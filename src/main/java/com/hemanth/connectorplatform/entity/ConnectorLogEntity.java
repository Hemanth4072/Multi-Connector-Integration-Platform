package com.hemanth.connectorplatform.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity @Table(name = "connector_logs") @Data
public class ConnectorLogEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String connectorName; private String operation; private String requestId; private String userId; private String status;
    @Column(columnDefinition = "TEXT") private String requestPayload;
    @Column(columnDefinition = "TEXT") private String responsePayload;
    @Column(columnDefinition = "TEXT") private String errorMessage;
    private Integer executionTimeMs;
    @CreationTimestamp private Instant createdAt;
}
