package uz.mk.codingbatcomrestfullapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.mk.codingbatcomrestfullapi.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question extends AbsEntity {
    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String method;

    @Column(columnDefinition = "TEXT")
    private String hint;

    private boolean star = false;

    @ManyToOne
    private Section section;

}
