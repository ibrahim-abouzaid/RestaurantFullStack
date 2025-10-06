package com.restaurant.restaurant.base.mapper;

import org.mapstruct.Mapper;

import java.util.List;


public interface BaseMapper<T,DTO> {


    T toEntity(DTO dto);
    DTO toDto(T t);

    List<T> toListEntity(List<DTO> dtos);
    List<DTO> toListDto(List<T> ts);

}
