package com.example.storeservice.mapper;

import com.example.storeservice.dto.StoreRequestDto;
import com.example.storeservice.entity.Store;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    Store toEntity(StoreRequestDto dto);
    StoreRequestDto toDto(Store entity);
}
