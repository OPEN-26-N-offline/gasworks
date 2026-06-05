package com.example.gasworks_worker_ui.entity;


import lombok.Data;

@Data
public class RouteInfo {
    private Integer remainingCount;
    private Integer estimatedMinutes;
    private String routeStatus;

    public RouteInfo(
        Integer remainingCount,
        Integer estimatedMinutes,
        String routeStatus
    ){
        this.remainingCount = remainingCount;
        this.estimatedMinutes = estimatedMinutes;
        this.routeStatus = routeStatus;
    }
}
