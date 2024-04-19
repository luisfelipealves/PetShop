package common.dto;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {

    @Nullable
    private Long id;

    @Nonnull
    private String name;

    @Nonnull
    private BreedDTO breed;
}
