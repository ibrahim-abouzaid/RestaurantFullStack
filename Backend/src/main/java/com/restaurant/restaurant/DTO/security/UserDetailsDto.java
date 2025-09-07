package com.restaurant.restaurant.DTO.security;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDetailsDto {
    private Long id;
    @NotEmpty(message = "not_empty.phone_number")
    private String phoneNumber;
    @Min(value = 16, message = "min.age")
    @Max(value = 80, message = "max.age")
    private int age;
    @NotEmpty(message = "not_empty.address")
    private String address;
}