package uz.mk.codingbatcomrestfullapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.mk.codingbatcomrestfullapi.entity.Subject;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {
    @NotNull(message = "The name mustn't be empty")
    private String name;

    @NotNull(message = "The subject id mustn't be empty")
    private Integer subjectId;

    @NotNull(message = "The description mustn't be empty")
    private String description;
}
