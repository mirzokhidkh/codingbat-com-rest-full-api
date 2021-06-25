package uz.mk.codingbatcomrestfullapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleDto {
    @NotNull(message = "The text mustn't be empty")
    private String text;

    @NotNull(message = "The question Id mustn't be empty")
    private Integer questionId;
}
