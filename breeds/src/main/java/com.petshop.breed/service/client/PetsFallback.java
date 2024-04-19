package com.petshop.breed.service.client;

import common.dto.PetDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PetsFallback implements PetsFeignClient{

    @Override
    public ResponseEntity<PetDTO> getPet(String correlationId, String uuid) {
        return null;
    }
}
