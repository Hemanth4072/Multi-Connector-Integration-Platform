package com.hemanth.connectorplatform.controller;

import com.hemanth.connectorplatform.dto.ApiResponse;
import com.hemanth.connectorplatform.dto.ConnectorRequest;
import com.hemanth.connectorplatform.service.ConnectorManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/razorpay")
@RequiredArgsConstructor
public class RazorpayController {
    private final ConnectorManagementService service;
    @PostMapping("/payment/create") public ApiResponse<Object> create(@RequestBody Map<String,Object> p){ return exec("createPaymentOrder", p); }
    @PostMapping("/payment/capture") public ApiResponse<Object> capture(@RequestBody Map<String,Object> p){ return exec("capturePayment", p); }
    @PostMapping("/refund") public ApiResponse<Object> refund(@RequestBody Map<String,Object> p){ return exec("createRefund", p); }
    @PostMapping("/webhook") public ApiResponse<Object> webhook(@RequestBody Map<String,Object> p){ return ApiResponse.ok(Map.of("received", true, "payload", p)); }
    private ApiResponse<Object> exec(String op, Map<String,Object> payload){ ConnectorRequest r = new ConnectorRequest(); r.setOperation(op); r.setPayload(payload); return ApiResponse.ok(service.execute("razorpay", r)); }
}
