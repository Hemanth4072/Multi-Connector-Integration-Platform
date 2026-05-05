package com.hemanth.connectorplatform.core;

import java.util.Map;

public interface Connector {
    String name();
    void initialize(ConnectorConfig config);
    Object execute(String operation, Map<String, Object> payload);
    boolean validateCredentials();
    boolean isHealthy();
}
