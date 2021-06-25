package uz.mk.codingbatcomrestfullapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mk.codingbatcomrestfullapi.entity.Section;

public interface SectionRepository extends JpaRepository<Section, Integer> {
    boolean existsByNameAndSubjectId(String name, Integer subject_id);
    boolean existsByNameAndSubjectIdAndIdNot(String name, Integer subject_id, Integer id);
}
