package com.example.storeservice.mapper;

import com.example.storeservice.dto.MenuRequestDto;
import com.example.storeservice.dto.MenuResponseDto;
import com.example.storeservice.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mapping(target = "id", ignore = true)      // 생성 시 ID는 자동 생성
    @Mapping(target = "store", ignore = true)   // service 계층에서 주입
    Menu toEntity(MenuRequestDto dto);

    @Mapping(target = "discountRate", ignore = true)
    @Mapping(target = "discountedPrice", ignore = true)
    MenuResponseDto toDto(Menu menu);
}
