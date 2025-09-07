package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.DTO.ChefDto;
import com.restaurant.restaurant.DTO.ContactInfoDto;
import com.restaurant.restaurant.Service.ContactInfoService;
import com.restaurant.restaurant.model.ContactInfo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactInfoController {
    private ContactInfoService  contactInfoService;
@Autowired
    public ContactInfoController(ContactInfoService contactInfoService) {
        this.contactInfoService = contactInfoService;
    }

    @PostMapping("/save_contactInfo")
    public ResponseEntity<ContactInfoDto> saveContact(@RequestBody @Valid ContactInfoDto contactInfoDto){
        return ResponseEntity.ok().body(contactInfoService.saveContactInfo(contactInfoDto));
    }

}
