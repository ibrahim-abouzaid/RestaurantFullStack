package com.restaurant.restaurant.Service.Impl;

import com.restaurant.restaurant.DTO.ChefDto;
import com.restaurant.restaurant.Mapper.ChefMapper;
import com.restaurant.restaurant.Repo.ChefRepo;
import com.restaurant.restaurant.Service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {

    private ChefRepo chefRepo;
    private ChefMapper chefMapper;

    @Autowired
    public ChefServiceImpl(ChefRepo chefRepo, ChefMapper chefMapper) {
        this.chefRepo = chefRepo;
        this.chefMapper = chefMapper;
    }

    @Override
    public List<ChefDto> getAllChefs() {


        return chefMapper.toListOfChefDto(chefRepo.findAll());
    }
}
