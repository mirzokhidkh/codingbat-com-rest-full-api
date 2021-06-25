package uz.mk.codingbatcomrestfullapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.mk.codingbatcomrestfullapi.entity.Subject;
import uz.mk.codingbatcomrestfullapi.payload.ApiResponse;
import uz.mk.codingbatcomrestfullapi.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    public ApiResponse add(Subject subject) {
        boolean exists = subjectRepository.existsByName(subject.getName());
        if (exists) {
            return new ApiResponse("A subject with such a name already exists", false);
        }
        subjectRepository.save(subject);
        return new ApiResponse("Subject saved", true);
    }

    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    public Subject getOneById(Integer id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isEmpty()) {
            return new Subject();
        }
        return optionalSubject.get();
    }

    public ApiResponse edit(Integer id, Subject subject) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isEmpty()) {
            return new ApiResponse("Subject not found", false);
        }

        boolean exists = subjectRepository.existsByNameAndIdNot(subject.getName(), id);
        if (exists) {
            return new ApiResponse("A subject with such a name already exists", false);
        }

        Subject editingSubject = optionalSubject.get();
        editingSubject.setName(subject.getName());
        subjectRepository.save(editingSubject);
        return new ApiResponse("Subject edited", true);
    }

    public ApiResponse deleteById(Integer id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isEmpty()) {
            return new ApiResponse("Subject not found", false);
        }
        subjectRepository.deleteById(id);
        return new ApiResponse("Subject deleted", true);
    }
}

