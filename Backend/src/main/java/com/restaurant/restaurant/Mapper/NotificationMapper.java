package com.restaurant.restaurant.Mapper;

import com.restaurant.restaurant.DTO.NotificationDto;
import com.restaurant.restaurant.base.mapper.BaseMapper;
import com.restaurant.restaurant.model.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper extends BaseMapper<Notification, NotificationDto> {

}
