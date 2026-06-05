package com.example.gasworks_worker_ui.controller;

import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
//@RequestMapping("/menu")
public class MenuController {
    @GetMapping("/menu")
    public String moveMenu() {
        return "index";
    }
    
    @GetMapping("/customer")
    public String moveCustomer() {
        return "customer_list";
    }
    
    // @GetMapping("/map")
    // public String moveMap() {
    //     return "map";
    // }

    // @GetMapping("/notice")
    // public String moveNotice() {
    //     return "notifications";
    // }

    @GetMapping("/sync")
    public String moveSync() {
        return "sync";
    }

    @GetMapping("/emergency")
    public String moveEmergency() {
        return "emergency";
    }

    @GetMapping("/settings")
    public String moveSettings() {
        return "settings";
    }
    

}
