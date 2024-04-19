package com.petshop.breed.controller;

import com.petshop.breed.service.BreedService;
import common.dto.BreedDTO;
import common.dto.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for Breeds in PetShop",
        description = "CRUD REST APIs in PestShop to CREATE, UPDATE, FETCH AND DELETE breed details"
)
@RestController
@RequestMapping(path="/breeds/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class BreedsController {

    private static final Logger logger = LoggerFactory.getLogger(BreedsController.class);
    private final BreedService breedService;

    @Operation(
            summary = "Create a new Breed REST API",
            description = "REST API to create a new Breed for the PetShop app"
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
    public ResponseEntity<BreedDTO> createBreed(@RequestHeader("correlation-id") String correlationId, @Valid @RequestBody BreedDTO breedDTO) {
        logger.info("Correlation-Id found {}", correlationId);
        BreedDTO savedBreedDTO = breedService.createBreed(breedDTO, correlationId);
        return ResponseEntity.ok(savedBreedDTO);
    }

    @Operation(
            summary = "Get a Breed REST API",
            description = "REST API to get a Breed for the PetShop app"
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
    public ResponseEntity<BreedDTO> getBreed(@RequestHeader("correlation-id") String correlationId, @NotNull @RequestParam String breedUuid) {
        logger.info("Correlation-Id found {}", correlationId);
        BreedDTO breedDTO = breedService.getBreed(breedUuid, correlationId);
        return ResponseEntity.ok(breedDTO);
    }

    @Operation(
            summary = "List all Breeds REST API",
            description = "REST API to list all Breeds for PetShop app"
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
    public ResponseEntity<List<BreedDTO>> listBreeds(@RequestHeader("correlation-id") String correlationId) {
        logger.info("Correlation-Id found {}", correlationId);
        List<BreedDTO> petList = new ArrayList<>(breedService.findAllBreeds(correlationId));
        return ResponseEntity.ok(petList);
    }

    @Operation(
            summary = "Update Breeds REST API",
            description = "REST API to update Breeds for PetShop app"
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
    public ResponseEntity<BreedDTO> updateBreed(@RequestHeader("correlation-id") String correlationId, @Valid @RequestBody BreedDTO breedDTO) {
        logger.info("Correlation-Id found {}", correlationId);
        BreedDTO savedBreed = breedService.updateBreed(breedDTO, correlationId);
        return ResponseEntity.ok(savedBreed);
    }

    @Operation(
            summary = "Delete Breeds REST API",
            description = "REST API to delete Breeds for PetShop app"
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
    public ResponseEntity<String> deleteBreed(@RequestHeader("correlation-id") String correlationId, @Valid @RequestParam String breedUuid) {
        logger.info("Correlation-Id found {}", correlationId);
        breedService.deleteBreed(breedUuid, correlationId);
        return ResponseEntity.ok(breedUuid);
    }
}