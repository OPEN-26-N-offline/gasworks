package com.example.gasworks_worker_ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.gasworks_worker_ui.repository.RouteInfoRepository;
import com.example.gasworks_worker_ui.repository.RoutePointRepository;

import org.springframework.ui.Model;

@Controller
public class RouteController {
    private final RouteInfoRepository routeInfoRepository;
    private final RoutePointRepository routePointRepository;

    public RouteController(RouteInfoRepository routeInfoRepository, RoutePointRepository routePointRepository){
        this.routeInfoRepository = routeInfoRepository;
        this.routePointRepository = routePointRepository;
    }
    
    @GetMapping("/map")
    public String updateMap(Model model) {
        model.addAttribute(
                "routeInfo",
                routeInfoRepository.getTodaysInfo());

        model.addAttribute(
                "routePoints",
                routePointRepository.findTodayRoute());
        return "map";
    }
}
