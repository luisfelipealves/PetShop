package com.petshop.pet.mapper;

import com.petshop.pet.entity.PetEntity;
import common.dto.PetDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetMapper {
    PetDTO toPetDTO(PetEntity petEntity);

    PetEntity toPetEntity(PetDTO petDTO);
}
