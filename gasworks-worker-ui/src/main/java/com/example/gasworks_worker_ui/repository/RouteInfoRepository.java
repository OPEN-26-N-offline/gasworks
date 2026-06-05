package com.example.gasworks_worker_ui.repository;

import org.springframework.stereotype.Repository;

import com.example.gasworks_worker_ui.entity.RouteInfo;

@Repository
public class RouteInfoRepository {
    public RouteInfo getTodaysInfo(){
        return new RouteInfo(
            4,
            120,
            "計算済"
        );
    }
}
