package com.petshop.breed.service.client;

import common.dto.PetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pets", fallback = PetsFallback.class)
public interface PetsFeignClient {
    @GetMapping(value = "/fetch", consumes = "application/json")
    ResponseEntity<PetDTO> getPet(@RequestHeader("correlation-id") String correlationId, @RequestParam String uuid);
}
