package com.hemanth.connectorplatform.core;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ConnectorMetadata {
    private String name;
    private String description;
    private Set<String> supportedOperations;
}
