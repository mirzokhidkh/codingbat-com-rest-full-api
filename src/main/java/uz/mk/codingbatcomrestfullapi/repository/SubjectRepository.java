package uz.mk.codingbatcomrestfullapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mk.codingbatcomrestfullapi.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
