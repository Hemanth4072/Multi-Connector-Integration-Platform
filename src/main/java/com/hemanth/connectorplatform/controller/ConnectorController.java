package com.hemanth.connectorplatform.controller;

import com.hemanth.connectorplatform.dto.ApiResponse;
import com.hemanth.connectorplatform.dto.ConnectorRequest;
import com.hemanth.connectorplatform.entity.ConnectorConfigEntity;
import com.hemanth.connectorplatform.service.ConnectorManagementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/connectors")
@RequiredArgsConstructor
public class ConnectorController {
    private final ConnectorManagementService service;
    @PostMapping("/configure") public ApiResponse<String> configure(@RequestBody ConnectorConfigEntity e){ service.configure(e); return ApiResponse.ok("Configured"); }
    @GetMapping public ApiResponse<Object> list(){ return ApiResponse.ok(service.listConfigured()); }
    @GetMapping("/{name}/health") public ApiResponse<Boolean> health(@PathVariable String name){ return ApiResponse.ok(service.health(name)); }
    @PostMapping("/{name}/execute") public ApiResponse<Object> execute(@PathVariable String name, @Valid @RequestBody ConnectorRequest req){ return ApiResponse.ok(service.execute(name, req)); }
    @DeleteMapping("/{name}") public ApiResponse<String> remove(@PathVariable String name){ service.remove(name); return ApiResponse.ok("Removed"); }
}
