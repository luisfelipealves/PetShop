package com.petshop.breed.mapper;

import com.petshop.breed.entity.BreedEntity;
import common.dto.BreedDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BreedMapper {
    BreedDTO toBreedDTO(BreedEntity breedEntity);

    BreedEntity toBreedEntity(BreedDTO breedDTO);
}
