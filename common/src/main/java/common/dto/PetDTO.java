package common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Pet",
        description = "Schema to hold Pet response information"
)public class PetDTO {

    @Nullable
    @Schema(
            description = "Unique Pet identifier", example = "8d1f4c10-2e9f-4640-8db3-dde82e430d9a"
    )
    private String uuid;

    @NotEmpty(message = "Pet name can not be a null or empty")
    @Schema(
            description = "Pet name", example = "Thor"
    )
    private String name;

    @NotEmpty(message = "Breed can not be a null or empty")
    @Schema(
            description = "Breed"
    )
    private BreedDTO breed;
}
