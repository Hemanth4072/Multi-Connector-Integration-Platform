package com.hemanth.connectorplatform.connectors.hubspot;

import com.hemanth.connectorplatform.core.Connector;
import com.hemanth.connectorplatform.core.ConnectorConfig;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HubSpotConnector implements Connector {
    private ConnectorConfig config;
    @Override public String name() { return "hubspot"; }
    @Override public void initialize(ConnectorConfig config) { this.config = config; }
    @Override public Object execute(String operation, Map<String, Object> payload) { return Map.of("operation", operation, "payload", payload, "connector", "hubspot"); }
    @Override public boolean validateCredentials() { return config != null && (config.getOauthToken() != null || config.getApiKey() != null); }
    @Override public boolean isHealthy() { return validateCredentials(); }
}
