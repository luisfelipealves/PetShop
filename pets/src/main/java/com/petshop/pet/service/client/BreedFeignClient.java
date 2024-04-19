package com.petshop.pet.service.client;

import common.dto.BreedDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "breeds", fallback = BreedFallback.class)
public interface BreedFeignClient {
    @GetMapping(value = "/breeds/api/get", consumes = "application/json")
    ResponseEntity<BreedDTO> getBreed(@RequestHeader("correlation-id") String correlationId, @RequestParam String breedUuid);
}
