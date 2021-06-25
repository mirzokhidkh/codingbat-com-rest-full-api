package uz.mk.codingbatcomrestfullapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import uz.mk.codingbatcomrestfullapi.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Section extends AbsEntity {
    @ManyToOne(optional = false)
    private Subject subject;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
}
