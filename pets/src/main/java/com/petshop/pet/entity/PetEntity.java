package com.petshop.pet.entity;

import common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PET")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PET_ID", nullable = false)
    private Long id;

    @Column(name = "PET_NM_NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "BREE_UUID", length = 36, nullable = false)
    private String breedUuid;
}