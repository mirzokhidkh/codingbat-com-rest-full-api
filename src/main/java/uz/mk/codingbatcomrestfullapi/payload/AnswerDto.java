package uz.mk.codingbatcomrestfullapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    @NotNull(message = "The text mustn't be empty")
    private String text;

    @NotNull(message = "The question id mustn't be empty")
    private Integer questionId;

    @NotNull(message = "The user id mustn't be empty")
    private Integer userId;

    private boolean correct = false;

}
