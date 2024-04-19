package com.petshop.pet.repository;

import com.petshop.pet.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepo extends JpaRepository<PetEntity, Long> {
    Optional<List<PetEntity>> findByNameContainsIgnoreCase(String name);
    Optional<PetEntity> findByUuid(String uuid);
    Optional<List<PetEntity>> findByBreedUuid(String breedUuid);
}