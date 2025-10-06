package com.restaurant.restaurant.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    @NotEmpty(message = "message.not.empty")
    private String message;
    @NotEmpty(message = "type.not.empty")
    private String type;
    private boolean isGlobal = true;


}
