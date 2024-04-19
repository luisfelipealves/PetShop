package com.petshop.pet.service.client;

import common.dto.BreedDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class BreedFallback implements BreedFeignClient {

    @Override
    public ResponseEntity<BreedDTO> getBreed(String correlationId, String uuid) {
        return null;
    }
}
