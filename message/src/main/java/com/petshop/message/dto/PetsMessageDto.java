package com.petshop.message.dto;

import java.util.UUID;

public record PetsMessageDto(UUID petUuid, String petName, String breedName) {
}
