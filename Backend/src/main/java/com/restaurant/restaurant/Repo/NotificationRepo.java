package com.restaurant.restaurant.Repo;

import com.restaurant.restaurant.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Long> {
    List<Notification> findAllByOrderByCreatedAtDesc();
}
