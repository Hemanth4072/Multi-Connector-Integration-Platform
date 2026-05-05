package com.hemanth.connectorplatform.controller;

import com.hemanth.connectorplatform.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/hubspot")
public class HubSpotController {
    @PostMapping("/contacts/create") public ApiResponse<Object> createContact(@RequestBody Map<String, Object> p){ return ApiResponse.ok(p); }
    @PutMapping("/contacts/{id}") public ApiResponse<Object> updateContact(@PathVariable String id, @RequestBody Map<String, Object> p){ return ApiResponse.ok(Map.of("id", id, "payload", p)); }
    @GetMapping("/contacts/search") public ApiResponse<Object> search(@RequestParam String email){ return ApiResponse.ok(Map.of("email", email)); }
    @PostMapping("/deals/create") public ApiResponse<Object> createDeal(@RequestBody Map<String, Object> p){ return ApiResponse.ok(p); }
    @PostMapping("/webhook") public ApiResponse<Object> webhook(@RequestBody Map<String, Object> p){ return ApiResponse.ok(Map.of("received", true, "payload", p)); }
}
