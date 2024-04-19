package com.petshop.pet.controller;

import com.petshop.pet.service.PetService;
import common.dto.ErrorResponseDto;
import common.dto.PetDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for Pets in PetShop",
        description = "CRUD REST APIs in PestShop to CREATE, UPDATE, FETCH AND DELETE pets details"
)
@RestController
@RequestMapping(path="/pets/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class PetsController {

    private final PetService petService;

    @Operation(
            summary = "Create a new Pet REST API",
            description = "REST API to create a new Pet for the PetShop app"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "201",
                            description = "HTTP Status CREATED"

                    ),
                    @ApiResponse(
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @PostMapping("/create")
    public ResponseEntity<PetDTO> createPet(@RequestHeader("correlation-id") String correlationId, @Valid @RequestBody PetDTO petDTO) {
        PetDTO savedPet = petService.createPet(petDTO, correlationId);
        return ResponseEntity.ok(savedPet);
    }

    @Operation(
            summary = "Delete Pets REST API",
            description = "REST API to delete a Pet for PetShop app"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP status OK"

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "HTTP status NOT_FOUND"

                    ),
                    @ApiResponse(
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePet(@RequestHeader("correlation-id") String correlationId, @NotNull @RequestParam String uuid) {
        petService.deletePet(uuid, correlationId);
        return null;
    }

    @Operation(
            summary = "Get all Pets with of a Breed REST API",
            description = "REST API to search all Pets from a Breed for the PetShop app"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status OK"

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "HTTP status NOT_FOUND"

                    ),
                    @ApiResponse(
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @GetMapping("/findPetsByBreed")
    public ResponseEntity<List<PetDTO>> findPetsByBreed(@RequestHeader("correlation-id") String correlationId, @NotNull @RequestParam String breedUuid) {
        List<PetDTO> petList = petService.findPetsByBreed(breedUuid, correlationId);
        return ResponseEntity.ok(petList);
    }

    @Operation(
            summary = "Get all Pets with a name REST API",
            description = "REST API to search all Pets from a name for the PetShop app"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status OK"

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "HTTP status NOT_FOUND"

                    ),
                    @ApiResponse(
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @GetMapping("/findPetsByName")
    public ResponseEntity<List<PetDTO>> findPetsByName(@RequestHeader("correlation-id") String correlationId, @NotNull @RequestParam String name) {
        List<PetDTO> petList = petService.findPetsByName(name, correlationId);
        return ResponseEntity.ok(petList);
    }

    @Operation(
            summary = "Get a Pet REST API",
            description = "REST API to get a Pet for the PetShop app"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status OK"

                    ),
                    @ApiResponse(
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @GetMapping("/get")
    public ResponseEntity<PetDTO> getPet(@RequestHeader("correlation-id") String correlationId, @NotNull @RequestParam String uuid) {
        PetDTO pet = petService.findPetById(uuid, correlationId);
        return ResponseEntity.ok(pet);
    }

    @Operation(
            summary = "List all Pets REST API",
            description = "REST API to list all Pets for PetShop app"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP status OK"

                    ),
                    @ApiResponse(
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @GetMapping("/findAll")
    public ResponseEntity<List<PetDTO>> listPets(@RequestHeader("correlation-id") String correlationId) {
        List<PetDTO> petList = new ArrayList<>(petService.findAll(correlationId));
        return ResponseEntity.ok(petList);
    }

    @Operation(
            summary = "Update Pets REST API",
            description = "REST API to update Pets for PetShop app"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP status OK"

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "HTTP status NOT_FOUND"

                    ),
                    @ApiResponse(
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @PutMapping("/update")
    public ResponseEntity<PetDTO> updatePet(@RequestHeader("correlation-id") String correlationId, @NotNull String uuid,
                                            @Valid @RequestBody PetDTO petDTO) {
        PetDTO savedPet = petService.updatePet(uuid, petDTO, correlationId);
        return ResponseEntity.ok(savedPet);
    }
}