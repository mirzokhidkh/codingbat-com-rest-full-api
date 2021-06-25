package uz.mk.codingbatcomrestfullapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.mk.codingbatcomrestfullapi.entity.Section;
import uz.mk.codingbatcomrestfullapi.entity.Subject;
import uz.mk.codingbatcomrestfullapi.payload.ApiResponse;
import uz.mk.codingbatcomrestfullapi.payload.SectionDto;
import uz.mk.codingbatcomrestfullapi.repository.SectionRepository;
import uz.mk.codingbatcomrestfullapi.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {
    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public ApiResponse add(SectionDto sectionDto) {
        Optional<Subject> optionalSubject = subjectRepository.findById(sectionDto.getSubjectId());
        if (optionalSubject.isEmpty()) {
            return new ApiResponse("Subject not found", false);
        }

        boolean exists = sectionRepository.existsByNameAndSubjectId(sectionDto.getName(), sectionDto.getSubjectId());
        if (exists) {
            return new ApiResponse("A Section with such a name and subject ID already exists", false);
        }

        Section section = new Section();
        section.setName(sectionDto.getName());
        section.setSubject(optionalSubject.get());
        section.setDescription(sectionDto.getDescription());
        sectionRepository.save(section);
        return new ApiResponse("Section saved", true);
    }

    public List<Section> getAll() {
        return sectionRepository.findAll();
    }

    public Section getOneById(Integer id) {
        Optional<Section> optionalSection = sectionRepository.findById(id);
        if (optionalSection.isEmpty()) {
            return new Section();
        }
        return optionalSection.get();
    }

    public ApiResponse edit(Integer id, SectionDto sectionDto) {
        Optional<Section> optionalSection = sectionRepository.findById(id);
        if (optionalSection.isEmpty()) {
            return new ApiResponse("Section not found", false);
        }

        Optional<Subject> optionalSubject = subjectRepository.findById(sectionDto.getSubjectId());
        if (optionalSubject.isEmpty()) {
            return new ApiResponse("Subject not found", false);
        }

        boolean exists = sectionRepository.existsByNameAndSubjectIdAndIdNot(sectionDto.getName(), sectionDto.getSubjectId(), id);
        if (exists) {
            return new ApiResponse("A Section with such a name and subject ID already exists", false);
        }

        Section section = optionalSection.get();
        section.setName(sectionDto.getName());
        section.setSubject(optionalSubject.get());
        section.setDescription(sectionDto.getDescription());
        sectionRepository.save(section);
        return new ApiResponse("Section edited", true);
    }

    public ApiResponse deleteById(Integer id) {
        Optional<Section> optionalSection = sectionRepository.findById(id);
        if (optionalSection.isEmpty()) {
            return new ApiResponse("Section not found", false);
        }
        sectionRepository.deleteById(id);
        return new ApiResponse("Section deleted", true);
    }
}

