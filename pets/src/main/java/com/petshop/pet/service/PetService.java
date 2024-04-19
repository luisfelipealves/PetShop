package com.petshop.pet.service;


import com.petshop.pet.entity.PetEntity;
import com.petshop.pet.mapper.PetMapper;
import com.petshop.pet.repository.PetRepo;
import com.petshop.pet.service.client.BreedFeignClient;
import common.dto.BreedDTO;
import common.dto.PetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepo petRepo;
    private final PetMapper petMapper;
    private final BreedFeignClient breedFeignClient;

    public List<PetDTO> findPetsByName(String name, String correlationId) {
        return petRepo.findByNameContainsIgnoreCase(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No pets found"))
                .stream()
                .map(petEntity -> getPetDTO(correlationId, petEntity))
                .collect(Collectors.toList());
    }

    public List<PetDTO> findPetsByBreed(String breedUuid, String correlationId) {
        return petRepo.findByBreedUuid(breedUuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No pets found"))
                .stream()
                .map(petEntity -> getPetDTO(correlationId, petEntity))
                .collect(Collectors.toList());
    }

    public PetDTO findPetById(String uuid, String correlationId) {
        PetEntity petEntity = petRepo.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Pet found with the id provided"));
        PetDTO petDTO = petMapper.toPetDTO(petEntity);

        BreedDTO breedDTO = breedFeignClient.getBreed(correlationId, petEntity.getBreedUuid()).getBody();
        if (null != breedDTO) {
            petDTO.setBreed(breedDTO);
        }

        return petDTO;
    }

    public PetDTO createPet(PetDTO petDTO, String correlationId) {
        PetEntity petEntity = petMapper.toPetEntity(petDTO);
        petEntity.setBreedUuid(petDTO.getBreed().getUuid());
        petEntity = petRepo.save(petEntity);
        PetDTO savedPetDTO = petMapper.toPetDTO(petEntity);
        BreedDTO breedDTO = breedFeignClient.getBreed(correlationId, petDTO.getBreed().getUuid()).getBody();
        savedPetDTO.setBreed(breedDTO);
        return savedPetDTO;
    }

    public PetDTO updatePet(String uuid, PetDTO petDTO, String correlationId) {

        PetEntity petEntity = petRepo.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found"));
        assert petDTO.getBreed().getUuid() != null;

        petEntity.setName(petDTO.getName());
        petEntity.setBreedUuid(petDTO.getBreed().getUuid());
        petEntity = petRepo.save(petEntity);

        return petMapper.toPetDTO(petEntity);
    }

    public void deletePet(String uuid, String correlationId) {
        PetEntity petEntity = petRepo.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found"));
        petRepo.delete(petEntity);
    }

    public List<PetDTO> findAll(String correlationId) {
        return petRepo.findAll().stream().map(petEntity -> getPetDTO(correlationId, petEntity)).toList();
    }

    private PetDTO getPetDTO(String correlationId, PetEntity petEntity) {
        PetDTO petDTO = petMapper.toPetDTO(petEntity);
        String breedUuid = petEntity.getBreedUuid();
        ResponseEntity<BreedDTO> dtoResponseEntity = breedFeignClient.getBreed(correlationId, breedUuid);
        if (null != dtoResponseEntity) petDTO.setBreed(dtoResponseEntity.getBody());
        return petDTO;
    }
}
