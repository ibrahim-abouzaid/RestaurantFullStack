package com.restaurant.restaurant.Service.Impl;

import com.restaurant.restaurant.DTO.NotificationDto;
import com.restaurant.restaurant.Mapper.NotificationMapper;
import com.restaurant.restaurant.Repo.NotificationRepo;
import com.restaurant.restaurant.Service.NotificationService;
import com.restaurant.restaurant.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepo notificationRepo;
    private NotificationMapper notificationMapper;


    @Autowired
    public NotificationServiceImpl(NotificationRepo notificationRepo, NotificationMapper notificationMapper) {
        this.notificationRepo = notificationRepo;
        this.notificationMapper = notificationMapper;
    }


    @Override
    public boolean notifyAllUsers(NotificationDto notificationDto) {
      Notification notification= notificationRepo.save(notificationMapper.toEntity(notificationDto));
        return Objects.nonNull(notification);
    }

    @Override
    public List<NotificationDto> getAllNotifications() {
        return notificationMapper.toListDto( notificationRepo.findAllByOrderByCreatedAtDesc());
    }
}
