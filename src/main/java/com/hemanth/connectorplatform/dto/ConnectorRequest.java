package com.hemanth.connectorplatform.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Map;

@Data
public class ConnectorRequest {
    @NotBlank
    private String connectorName;
    private String operation;
    private Map<String, Object> payload;
}
