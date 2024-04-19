package com.petshop.breed.entity;

import common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BREED")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreedEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BREED_ID")
    private Long id;

    @Column(name = "BREE_NM_NAME", length = 100)
    private String name;
}