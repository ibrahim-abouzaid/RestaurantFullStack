package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.DTO.NotificationDto;
import com.restaurant.restaurant.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")

public class NotificationController {
    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/get-All-Notification")
    public ResponseEntity<List<NotificationDto>> getNotifications() {
        return ResponseEntity.ok().body( notificationService.getAllNotifications());
    }
}
