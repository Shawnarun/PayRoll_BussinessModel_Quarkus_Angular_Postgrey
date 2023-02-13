package com.example.Mapper;

import com.example.DTO.BusinessDTO;
import com.example.Entity.Business;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BusinessMapper {


    //-----DAO to DTO
    @Mapping(target = "name", expression = "java(business.getName())")
      BusinessDTO toDTO(Business business);

    //-----DTO to DAO
    @Mapping(target = "id", ignore = true)
    Business toDAO(BusinessDTO businessDTO);

    @Mapping(target = "id", ignore = true)
    void merge(@MappingTarget Business target, Business source);
}
