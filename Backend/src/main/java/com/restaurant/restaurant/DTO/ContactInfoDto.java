package com.restaurant.restaurant.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoDto {

    private Long id;
    private String name;
    private String email;
    private String subject;
    private String message;
}
