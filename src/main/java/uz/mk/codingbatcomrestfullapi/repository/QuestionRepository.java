package uz.mk.codingbatcomrestfullapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mk.codingbatcomrestfullapi.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    boolean existsByNameAndSectionId(String name, Integer section_id);
    boolean existsByNameAndSectionIdAndIdNot(String name, Integer section_id, Integer id);
}
