package com.hemanth.connectorplatform.service;

import com.hemanth.connectorplatform.core.*;
import com.hemanth.connectorplatform.dto.ConnectorRequest;
import com.hemanth.connectorplatform.entity.ConnectorConfigEntity;
import com.hemanth.connectorplatform.entity.ConnectorLogEntity;
import com.hemanth.connectorplatform.repository.ConnectorConfigRepository;
import com.hemanth.connectorplatform.repository.ConnectorLogRepository;
import com.hemanth.connectorplatform.util.RateLimiter;
import com.hemanth.connectorplatform.util.RetryHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConnectorManagementService {
    private final ConnectorFactory factory; private final ConnectorRegistry registry; private final ConnectorConfigRepository configRepo;
    private final ConnectorLogRepository logRepo; private final RateLimiter rateLimiter; private final RetryHelper retryHelper; private final ObjectMapper objectMapper;

    @Transactional
    public void configure(ConnectorConfigEntity entity) {
        configRepo.save(entity);
        Connector connector = factory.getConnector(entity.getConnectorName());
        connector.initialize(toConfig(entity));
        registry.register(connector);
    }
    public List<String> listConfigured() { return registry.list(); }
    public boolean health(String name) { return registry.get(name).isHealthy(); }
    @Transactional
    public Object execute(String name, ConnectorRequest request) {
        Connector connector = registry.get(name);
        ConnectorConfigEntity cfg = configRepo.findByConnectorNameIgnoreCase(name).orElseThrow();
        long start = System.currentTimeMillis();
        rateLimiter.checkLimit(name, cfg.getMaxRequestsPerMinute());
        Object result = retryHelper.withRetry(() -> connector.execute(request.getOperation(), request.getPayload()), cfg.getMaxRetries());
        logRepo.save(log(name, request.getOperation(), request.getPayload(), result, null, (int)(System.currentTimeMillis()-start), "SUCCESS"));
        return result;
    }
    @Transactional
    public void remove(String name) { configRepo.deleteByConnectorNameIgnoreCase(name); registry.remove(name); }
    private ConnectorConfig toConfig(ConnectorConfigEntity e) { return ConnectorConfig.builder().connectorName(e.getConnectorName()).apiKey(e.getApiKey()).apiSecret(e.getApiSecret()).baseUrl(e.getBaseUrl()).oauthToken(e.getOauthToken()).refreshToken(e.getRefreshToken()).tokenExpiresAt(e.getTokenExpiresAt()).webhookUrl(e.getWebhookUrl()).webhookSecret(e.getWebhookSecret()).maxRequestsPerMinute(e.getMaxRequestsPerMinute()).maxRetries(e.getMaxRetries()).enabled(e.getEnabled()).build(); }
    private ConnectorLogEntity log(String c, String op, Object req, Object res, String err, int ms, String status) { ConnectorLogEntity l = new ConnectorLogEntity(); l.setConnectorName(c); l.setOperation(op); l.setStatus(status); l.setRequestPayload(asJson(req)); l.setResponsePayload(asJson(res)); l.setErrorMessage(err); l.setExecutionTimeMs(ms); return l; }
    private String asJson(Object o){try{return objectMapper.writeValueAsString(o);}catch(Exception e){return String.valueOf(o);} }
}
