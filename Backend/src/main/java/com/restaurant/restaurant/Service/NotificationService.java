package com.restaurant.restaurant.Service;


import com.restaurant.restaurant.DTO.NotificationDto;

import java.util.List;

public interface NotificationService {
     boolean notifyAllUsers(NotificationDto notificationDto);
    List<NotificationDto> getAllNotifications();
}
