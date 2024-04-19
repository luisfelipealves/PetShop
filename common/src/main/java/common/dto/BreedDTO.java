package common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Breed",
        description = "Schema to hold Breed response information"
)
public class BreedDTO{

    @Nullable
    @Schema(
            description = "Unique Breed identifier", example = "8d1f4c10-2e9f-4640-8db3-dde82e430d9a"
    )
    private String uuid;

    @NotEmpty(message = "Breed name can not be a null or empty")
    @Schema(
            description = "Breed name", example = "Chihuahua"
    )    private String name;
}
