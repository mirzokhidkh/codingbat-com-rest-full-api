package uz.mk.codingbatcomrestfullapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.mk.codingbatcomrestfullapi.entity.Question;
import uz.mk.codingbatcomrestfullapi.entity.Section;
import uz.mk.codingbatcomrestfullapi.payload.ApiResponse;
import uz.mk.codingbatcomrestfullapi.payload.QuestionDto;
import uz.mk.codingbatcomrestfullapi.repository.QuestionRepository;
import uz.mk.codingbatcomrestfullapi.repository.SectionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    SectionRepository sectionRepository;

    public ApiResponse add(QuestionDto questionDto) {
        Optional<Section> optionalSection = sectionRepository.findById(questionDto.getSectionId());
        if (optionalSection.isEmpty()) {
            return new ApiResponse("Section not found", false);
        }

        boolean exists = questionRepository.existsByNameAndSectionId(questionDto.getName(), questionDto.getSectionId());
        if (exists) {
            return new ApiResponse("A Question with such a name and Section ID already exists", false);
        }

        Question question = new Question();
        question.setName(questionDto.getName());
        question.setText(questionDto.getText());
        question.setMethod(questionDto.getMethod());
        question.setHint(questionDto.getHint());
        question.setStar(questionDto.isStar());
        question.setSection(optionalSection.get());
        questionRepository.save(question);
        return new ApiResponse("Question saved", true);
    }

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public Question getOneById(Integer id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isEmpty()) {
            return new Question();
        }
        return optionalQuestion.get();
    }

    public ApiResponse edit(Integer id, QuestionDto questionDto) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isEmpty()) {
            return new ApiResponse("Question not found", false);
        }

        Optional<Section> optionalSection = sectionRepository.findById(questionDto.getSectionId());
        if (optionalSection.isEmpty()) {
            return new ApiResponse("Section not found", false);
        }

        boolean exists = questionRepository.existsByNameAndSectionIdAndIdNot(questionDto.getName(), questionDto.getSectionId(),id);
        if (exists) {
            return new ApiResponse("A Question with such a name and Section ID already exists", false);
        }

        Question question = optionalQuestion.get();
        question.setName(questionDto.getName());
        question.setText(questionDto.getText());
        question.setMethod(questionDto.getMethod());
        question.setHint(questionDto.getHint());
        question.setStar(questionDto.isStar());
        question.setSection(optionalSection.get());
        questionRepository.save(question);
        return new ApiResponse("Question edited", true);
    }

    public ApiResponse deleteById(Integer id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isEmpty()) {
            return new ApiResponse("Question not found", false);
        }
        questionRepository.deleteById(id);
        return new ApiResponse("Question deleted", true);
    }
}

