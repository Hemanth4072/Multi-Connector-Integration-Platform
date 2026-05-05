package com.hemanth.connectorplatform.core;

import com.hemanth.connectorplatform.exception.ConnectorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConnectorFactory {
    private final List<Connector> connectorStrategies;
    public Connector getConnector(String name) {
        return connectorStrategies.stream().filter(c -> c.name().equalsIgnoreCase(name)).findFirst()
                .orElseThrow(() -> new ConnectorException("Unsupported connector: " + name));
    }
}
