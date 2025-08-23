package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.DTO.ChefDto;
import com.restaurant.restaurant.Service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class ChefController {

    private ChefService chefService;
    @Autowired
    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    @GetMapping("/get-All-Chefs")
    public ResponseEntity<List<ChefDto>> getAllChefs(){
        return ResponseEntity.ok().body(chefService.getAllChefs());
    }
}
