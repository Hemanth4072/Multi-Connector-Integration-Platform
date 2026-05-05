package com.hemanth.connectorplatform.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ConnectorResponse {
    private String connector;
    private String operation;
    private Object result;
}
