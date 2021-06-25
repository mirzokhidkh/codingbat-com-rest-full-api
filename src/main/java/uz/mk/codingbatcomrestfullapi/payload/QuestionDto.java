package uz.mk.codingbatcomrestfullapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    @NotNull(message = "The name mustn't be empty")
    private String name;

    @NotNull(message = "The text mustn't be empty")
    private String text;

    @NotNull(message = "The method mustn't be empty")
    private String method;

    private String hint;

    private boolean star = false;

    @NotNull(message = "The section id mustn't be empty")
    private Integer sectionId;


}
