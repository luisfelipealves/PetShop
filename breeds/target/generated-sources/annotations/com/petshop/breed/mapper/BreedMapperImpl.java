package com.petshop.breed.mapper;

import com.petshop.breed.entity.BreedEntity;
import common.dto.BreedDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-19T10:03:26+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class BreedMapperImpl implements BreedMapper {

    @Override
    public BreedDTO toBreedDTO(BreedEntity breedEntity) {
        if ( breedEntity == null ) {
            return null;
        }

        BreedDTO.BreedDTOBuilder breedDTO = BreedDTO.builder();

        breedDTO.uuid( breedEntity.getUuid() );
        breedDTO.name( breedEntity.getName() );

        return breedDTO.build();
    }

    @Override
    public BreedEntity toBreedEntity(BreedDTO breedDTO) {
        if ( breedDTO == null ) {
            return null;
        }

        BreedEntity.BreedEntityBuilder breedEntity = BreedEntity.builder();

        breedEntity.name( breedDTO.getName() );

        return breedEntity.build();
    }
}
