package com.hemanth.connectorplatform.core;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ConnectorConfig {
    private String connectorName;
    private String apiKey;
    private String apiSecret;
    private String baseUrl;
    private String oauthToken;
    private String refreshToken;
    private Long tokenExpiresAt;
    private Map<String, Object> additionalConfig;
    private String webhookUrl;
    private String webhookSecret;
    private Integer maxRequestsPerMinute;
    private Integer maxRetries;
    private Boolean enabled;
}
