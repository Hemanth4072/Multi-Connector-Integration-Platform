package com.hemanth.connectorplatform.util;

import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class RetryHelper {
    public <T> T withRetry(Supplier<T> supplier, int maxRetries) {
        RuntimeException last = null;
        for (int i = 0; i <= maxRetries; i++) {
            try { return supplier.get(); } catch (RuntimeException ex) { last = ex; try { Thread.sleep((long) Math.pow(2, i) * 100L); } catch (InterruptedException ignored) { Thread.currentThread().interrupt(); } }
        }
        throw last;
    }
}
