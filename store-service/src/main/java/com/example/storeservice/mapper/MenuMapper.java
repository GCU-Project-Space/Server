package com.example.storeservice.mapper;

import com.example.storeservice.dto.MenuRequestDto;
import com.example.storeservice.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mapping(target = "store", ignore = true)  // store는 service에서 직접 주입
    Menu toEntity(MenuRequestDto dto);
}
