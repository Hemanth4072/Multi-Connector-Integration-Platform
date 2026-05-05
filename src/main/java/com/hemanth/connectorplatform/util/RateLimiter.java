package com.hemanth.connectorplatform.util;

import com.hemanth.connectorplatform.exception.ConnectorException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimiter {
    private final Map<String, AtomicInteger> counters = new ConcurrentHashMap<>();
    private final Map<String, Long> windows = new ConcurrentHashMap<>();
    public void checkLimit(String key, int maxPerMinute) {
        long now = Instant.now().getEpochSecond() / 60;
        windows.putIfAbsent(key, now);
        counters.putIfAbsent(key, new AtomicInteger(0));
        if (windows.get(key) != now) { windows.put(key, now); counters.get(key).set(0); }
        if (counters.get(key).incrementAndGet() > maxPerMinute) throw new ConnectorException("Rate limit exceeded for " + key);
    }
}
