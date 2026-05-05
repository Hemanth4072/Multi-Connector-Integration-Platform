package com.hemanth.connectorplatform.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class WebhookService {
    @Async
    public void process(String connector, Map<String, Object> payload) { log.info("Webhook received for {} payload={}", connector, payload); }
}
