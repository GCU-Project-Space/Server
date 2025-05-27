package com.example.storeservice.mapper;

import com.example.storeservice.dto.StoreRequestDto;
import com.example.storeservice.dto.StoreResponseDto;
import com.example.storeservice.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    Store toEntity(StoreRequestDto dto);
    StoreRequestDto toRequestDto(Store entity);
    StoreResponseDto toResponseDto(Store store);
}
