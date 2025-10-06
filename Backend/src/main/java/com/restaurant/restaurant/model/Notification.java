package com.restaurant.restaurant.model;

import com.restaurant.restaurant.base.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification extends BaseEntity {
    @Column(nullable = false)
    private String message;
    private String type;
    private boolean isGlobal = true;

}
