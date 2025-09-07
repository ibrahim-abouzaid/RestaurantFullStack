package com.restaurant.restaurant.Service.Impl;

import com.restaurant.restaurant.DTO.ContactInfoDto;
import com.restaurant.restaurant.Mapper.ContactInfoMapper;
import com.restaurant.restaurant.Repo.ContactInfoRepo;
import com.restaurant.restaurant.Service.ContactInfoService;
import com.restaurant.restaurant.model.ContactInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactInfoServiceImpl implements ContactInfoService {


  private ContactInfoRepo  contactInfoRepo;
    private ContactInfoMapper  contactInfoMapper;

    @Autowired
    public ContactInfoServiceImpl(ContactInfoRepo contactInfoRepo, ContactInfoMapper contactInfoMapper) {
        this.contactInfoRepo = contactInfoRepo;
        this.contactInfoMapper = contactInfoMapper;
    }

    @Override
    public ContactInfoDto saveContactInfo(ContactInfoDto contactInfoDto) {

        ContactInfo contactInfo=contactInfoMapper.toContactInfo(contactInfoDto);
        return contactInfoMapper.toContactInfoDto( contactInfoRepo.save(contactInfo));
    }
}
