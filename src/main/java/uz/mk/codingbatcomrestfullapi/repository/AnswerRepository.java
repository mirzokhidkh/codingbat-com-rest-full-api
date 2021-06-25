package uz.mk.codingbatcomrestfullapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mk.codingbatcomrestfullapi.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    boolean existsByTextAndUserId(String text, Integer user_id);
    boolean existsByTextAndUserIdAndIdNot(String text, Integer user_id, Integer id);

}
