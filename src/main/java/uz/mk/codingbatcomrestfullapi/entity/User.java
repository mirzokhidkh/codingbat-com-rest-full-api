package uz.mk.codingbatcomrestfullapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotBlank(message = "The email mustn't be empty")
    @Column(unique = true, nullable = false)
    private String email;

    @Min(value = 5,message = "The password must be at least 6 letter")
    @NotBlank(message = "The password mustn't be empty")
    @Column(nullable = false)
    private String password;


}
