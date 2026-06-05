package com.example.gasworks_worker_ui.entity;

import com.example.gasworks_worker_ui.Category.MapCategory;

import lombok.Data;

@Data
public class RoutePoint {
    private String id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Integer sequence;
    private MapCategory type;

    public RoutePoint(
        String id,
        String name,
        Double latitude,
        Double longitude,
        Integer sequence,
        MapCategory type
    ){
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sequence = sequence;
        this.type = type;
    }
}
