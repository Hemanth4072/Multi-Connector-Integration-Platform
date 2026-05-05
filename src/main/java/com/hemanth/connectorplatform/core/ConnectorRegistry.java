package com.hemanth.connectorplatform.core;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ConnectorRegistry {
    private final Map<String, Connector> connectors = new ConcurrentHashMap<>();
    public void register(Connector connector) { connectors.put(connector.name().toLowerCase(), connector); }
    public Connector get(String name) { return connectors.get(name.toLowerCase()); }
    public void remove(String name) { connectors.remove(name.toLowerCase()); }
    public List<String> list() { return connectors.keySet().stream().sorted().toList(); }
}
