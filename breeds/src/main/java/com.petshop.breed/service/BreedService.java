package com.petshop.breed.service;

import com.petshop.breed.entity.BreedEntity;
import com.petshop.breed.mapper.BreedMapper;
import com.petshop.breed.repository.BreedRepo;
import common.dto.BreedDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BreedService {

    private final BreedRepo breedRepo;
    private final BreedMapper breedMapper;
    private final static Logger logger = LoggerFactory.getLogger(BreedService.class);

    public BreedDTO createBreed(BreedDTO breedDTO, String correlationId) {
        logger.info("Correlation-Id found {}", correlationId);
        BreedEntity breedEntity = breedMapper.toBreedEntity(breedDTO);
        BreedEntity savedBreedEntity = breedRepo.save(breedEntity);
        return breedMapper.toBreedDTO(savedBreedEntity);
    }

    public List<BreedDTO> findAllBreeds(String correlationId) {
        logger.info("Correlation-Id found {}", correlationId);
        return  breedRepo.findAll()
                .stream()
                .map(breedMapper::toBreedDTO)
                .collect(Collectors.toList());
    }

    public BreedDTO getBreed(String breedUuid, String correlationId) {
        logger.info("Correlation-Id found {}", correlationId);
        try {
            UUID uuid = UUID.fromString(breedUuid);
        } catch (Exception e) {
            throw new ServiceException("Invalid Breed UUID");
        }
        BreedEntity breedEntity = breedRepo.findByUuid(breedUuid)
                .orElseThrow(() -> new ServiceException("Breed not found"));
        return breedMapper.toBreedDTO(breedEntity);
    }

    public BreedDTO updateBreed(BreedDTO breedDTO, String correlationId) {
        logger.info("Correlation-Id found {}", correlationId);
        BreedEntity breedEntity = breedRepo.findByUuid(breedDTO.getUuid())
                .orElseThrow(() -> new ServiceException("No Breed with this uuid found"));
        breedEntity.setName(breedDTO.getName());
        BreedEntity savedBredEntity = breedRepo.save(breedEntity);
        return  breedMapper.toBreedDTO(savedBredEntity);
    }

    public void deleteBreed(String breedUuid, String correlationId) {
        logger.info("Correlation-Id {}", correlationId);
        try {
            UUID uuid = UUID.fromString(breedUuid);
        } catch (Exception e) {
            throw new ServiceException("Invalid Breed UUID");
        }
        BreedEntity breedEntity = breedRepo.findByUuid(breedUuid)
                .orElseThrow(() -> new ServiceException("No Breed with this uuid found"));
        breedRepo.delete(breedEntity);
    }
}
