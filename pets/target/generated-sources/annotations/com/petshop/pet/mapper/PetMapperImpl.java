package com.petshop.pet.mapper;

import com.petshop.pet.entity.PetEntity;
import common.dto.PetDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-19T10:03:27+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class PetMapperImpl implements PetMapper {

    @Override
    public PetDTO toPetDTO(PetEntity petEntity) {
        if ( petEntity == null ) {
            return null;
        }

        PetDTO.PetDTOBuilder petDTO = PetDTO.builder();

        petDTO.id( petEntity.getId() );
        petDTO.name( petEntity.getName() );

        return petDTO.build();
    }

    @Override
    public PetEntity toPetEntity(PetDTO petDTO) {
        if ( petDTO == null ) {
            return null;
        }

        PetEntity.PetEntityBuilder petEntity = PetEntity.builder();

        petEntity.id( petDTO.getId() );
        petEntity.name( petDTO.getName() );

        return petEntity.build();
    }
}
