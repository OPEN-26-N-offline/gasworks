package com.example.gasworks_worker_ui.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.gasworks_worker_ui.entity.Notification;
import com.example.gasworks_worker_ui.repository.NotificationRepository;
import org.springframework.ui.Model;


@Controller
public class NotificationController {
    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }


    @GetMapping("/notice")
    public String updateNotice(Model model) {
        List<Notification> notifications= notificationRepository.findAll();
        model.addAttribute("notifications", notifications);
        
        return "notifications";
    }
    
    
}
