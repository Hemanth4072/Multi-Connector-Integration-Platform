package com.hemanth.connectorplatform.connectors.razorpay;

import com.hemanth.connectorplatform.core.Connector;
import com.hemanth.connectorplatform.core.ConnectorConfig;
import com.hemanth.connectorplatform.exception.ConnectorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RazorpayConnector implements Connector {
    private final RazorpayService service;
    private ConnectorConfig config;
    @Override public String name() { return "razorpay"; }
    @Override public void initialize(ConnectorConfig config) { this.config = config; }
    @Override public Object execute(String operation, Map<String, Object> payload) {
        return switch (operation) {
            case "createPaymentOrder" -> service.createPaymentOrder(config, payload);
            case "capturePayment" -> service.capturePayment(config, payload);
            case "createRefund" -> service.createRefund(config, payload);
            case "fetchPaymentDetails" -> service.fetchPaymentDetails(config, payload);
            case "createSubscription" -> service.createSubscription(config, payload);
            case "cancelSubscription" -> service.cancelSubscription(config, payload);
            default -> throw new ConnectorException("Unsupported Razorpay operation: " + operation);
        };
    }
    @Override public boolean validateCredentials() { return config != null && config.getApiKey() != null && config.getApiSecret() != null; }
    @Override public boolean isHealthy() { return validateCredentials(); }
}
