package com.hemanth.connectorplatform.connectors.razorpay;

import com.hemanth.connectorplatform.core.ConnectorConfig;
import com.hemanth.connectorplatform.exception.ConnectorException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RazorpayService {
    private final RestTemplate restTemplate;

    public Object createPaymentOrder(ConnectorConfig c, Map<String, Object> p) { return post(c, "/orders", p); }
    public Object capturePayment(ConnectorConfig c, Map<String, Object> p) { return post(c, "/payments/" + p.get("paymentId") + "/capture", p); }
    public Object createRefund(ConnectorConfig c, Map<String, Object> p) { return post(c, "/payments/" + p.get("paymentId") + "/refund", p); }
    public Object fetchPaymentDetails(ConnectorConfig c, Map<String, Object> p) { return get(c, "/payments/" + p.get("paymentId")); }
    public Object createSubscription(ConnectorConfig c, Map<String, Object> p) { return post(c, "/subscriptions", p); }
    public Object cancelSubscription(ConnectorConfig c, Map<String, Object> p) { return post(c, "/subscriptions/" + p.get("subscriptionId") + "/cancel", p); }

    private Object post(ConnectorConfig c, String path, Map<String, Object> payload) { return exchange(c, path, HttpMethod.POST, payload); }
    private Object get(ConnectorConfig c, String path) { return exchange(c, path, HttpMethod.GET, null); }
    private Object exchange(ConnectorConfig c, String path, HttpMethod method, Object payload) {
        if (c.getBaseUrl() == null) throw new ConnectorException("Razorpay base URL missing");
        HttpHeaders headers = new HttpHeaders();
        String token = Base64.getEncoder().encodeToString((c.getApiKey() + ":" + c.getApiSecret()).getBytes(StandardCharsets.UTF_8));
        headers.set("Authorization", "Basic " + token);
        ResponseEntity<Map> res = restTemplate.exchange(c.getBaseUrl() + path, method, new HttpEntity<>(payload, headers), Map.class);
        return res.getBody();
    }
}
