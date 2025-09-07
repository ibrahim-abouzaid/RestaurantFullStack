package com.restaurant.restaurant.Mapper;

import com.restaurant.restaurant.DTO.ContactInfoDto;
import com.restaurant.restaurant.model.ContactInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactInfoMapper {

    ContactInfoDto toContactInfoDto(ContactInfo contactInfo);
    ContactInfo toContactInfo(ContactInfoDto contactInfoDto);

    List<ContactInfoDto> toListOfContactInfoDto(List<ContactInfo>  contactInfos);
    List<ContactInfo> toListOfContactInfo(List<ContactInfoDto> contactInfoDtos);
}
