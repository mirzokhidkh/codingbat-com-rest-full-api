package uz.mk.codingbatcomrestfullapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mk.codingbatcomrestfullapi.entity.Example;

public interface ExampleRepository extends JpaRepository<Example, Integer> {

    boolean existsByTextAndQuestionId(String text, Integer question_id);
    boolean existsByTextAndQuestionIdAndIdNot(String text, Integer question_id,Integer id);

}
