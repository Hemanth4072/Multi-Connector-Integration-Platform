package com.hemanth.connectorplatform.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity @Table(name = "connector_configs") @Data
public class ConnectorConfigEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(unique = true, nullable = false) private String connectorName;
    private String apiKey; private String apiSecret; private String baseUrl;
    @Column(columnDefinition = "TEXT") private String oauthToken;
    @Column(columnDefinition = "TEXT") private String refreshToken;
    private Long tokenExpiresAt;
    @Column(columnDefinition = "JSON") private String additionalConfig;
    private String webhookUrl; private String webhookSecret;
    private Integer maxRequestsPerMinute = 60; private Integer maxRetries = 3; private Boolean enabled = true;
    @CreationTimestamp private Instant createdAt;
    @UpdateTimestamp private Instant updatedAt;
}
