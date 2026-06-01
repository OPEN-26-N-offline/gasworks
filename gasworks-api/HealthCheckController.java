package com.givery.gasworks.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@Tag(name = "Health Check", description = "システムの稼働状態を確認するAPI")
public class HealthCheckController {

    @Operation(summary = "ヘルスチェック", description = "APIが正常に動作しているか確認します。")
    @GetMapping("/api/health")
    public Map<String, String> healthCheck() {
        return Map.of("status", "UP", "message", "GasWorks API is running");
    }
}