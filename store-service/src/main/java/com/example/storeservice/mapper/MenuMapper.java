package com.example.storeservice.mapper;

import com.example.storeservice.dto.MenuRequestDto;
import com.example.storeservice.dto.MenuResponseDto;
import com.example.storeservice.dto.MenuPartialUpdateDto;
import com.example.storeservice.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mapping(target = "id", ignore = true)      // 생성 시 ID는 자동 생성
    @Mapping(target = "store", ignore = true)   // service 계층에서 주입
    @Mapping(target = "menuDiscount", ignore = true)
    Menu toEntity(MenuRequestDto dto);

    @Mapping(target = "discountRate", ignore = true)
    @Mapping(target = "discountedPrice", ignore = true)
    MenuResponseDto toDto(Menu menu);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "store", ignore = true)
    @Mapping(target = "menuDiscount", ignore = true)
    Menu toEntity(MenuPartialUpdateDto dto);

    @Named("mapOptionInfo")
    default Menu.OptionInfo mapOptionInfo(MenuPartialUpdateDto.OptionInfo optionInfo) {
        if (optionInfo == null) {
            return null;
        }
        Menu.OptionInfo mapped = new Menu.OptionInfo();
        mapped.setId(optionInfo.getId());
        mapped.setName(optionInfo.getName());
        mapped.setPrice(optionInfo.getPrice());
        return mapped;
    }
    @Named("mapOptionInfoList")
    default List<Menu.OptionInfo> mapOptionInfoList(List<MenuPartialUpdateDto.OptionInfo> optionInfos) {
        if (optionInfos == null) return null;
        return optionInfos.stream()
            .map(this::mapOptionInfo)
            .toList();
    }

}
