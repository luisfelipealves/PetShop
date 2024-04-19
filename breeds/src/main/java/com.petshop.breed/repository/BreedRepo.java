package com.petshop.breed.repository;

import com.petshop.breed.entity.BreedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BreedRepo extends JpaRepository<BreedEntity, Long> {
    Optional<BreedEntity> findByUuid(String breedUuid);
}