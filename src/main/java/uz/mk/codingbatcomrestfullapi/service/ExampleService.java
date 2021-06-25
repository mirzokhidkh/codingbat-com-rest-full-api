package uz.mk.codingbatcomrestfullapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.mk.codingbatcomrestfullapi.entity.Example;
import uz.mk.codingbatcomrestfullapi.entity.Question;
import uz.mk.codingbatcomrestfullapi.payload.ApiResponse;
import uz.mk.codingbatcomrestfullapi.payload.ExampleDto;
import uz.mk.codingbatcomrestfullapi.repository.ExampleRepository;
import uz.mk.codingbatcomrestfullapi.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {
    @Autowired
    ExampleRepository exampleRepository;

    @Autowired
    QuestionRepository questionRepository;

    public ApiResponse add(ExampleDto exampleDto) {
        Optional<Question> optionalQuestion = questionRepository.findById(exampleDto.getQuestionId());
        if (optionalQuestion.isEmpty()) {
            return new ApiResponse("Question not found", false);
        }

        boolean exists = exampleRepository.existsByTextAndQuestionId(exampleDto.getText(), exampleDto.getQuestionId());
        if (exists) {
            return new ApiResponse("A example with such a text and question ID already exists", false);
        }


        Example example = new Example();
        example.setText(exampleDto.getText());
        example.setQuestion(optionalQuestion.get());
        exampleRepository.save(example);
        return new ApiResponse("Example saved", true);
    }

    public List<Example> getAll() {
        return exampleRepository.findAll();
    }

    public Example getOneById(Integer id) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (optionalExample.isEmpty()) {
            return new Example();
        }
        return optionalExample.get();
    }

    public ApiResponse edit(Integer id, ExampleDto exampleDto) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (optionalExample.isEmpty()) {
            return new ApiResponse("Example not found", false);
        }

        Optional<Question> optionalQuestion = questionRepository.findById(exampleDto.getQuestionId());
        if (optionalQuestion.isEmpty()) {
            return new ApiResponse("Question not found", false);
        }

        boolean exists = exampleRepository.existsByTextAndQuestionIdAndIdNot(exampleDto.getText(), exampleDto.getQuestionId(),id);
        if (exists) {
            return new ApiResponse("A example with such a text and question ID already exists", false);
        }


        Example example = optionalExample.get();
        example.setText(exampleDto.getText());
        example.setQuestion(optionalQuestion.get());
        exampleRepository.save(example);
        return new ApiResponse("Example edited", true);
    }

    public ApiResponse deleteById(Integer id) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (optionalExample.isEmpty()) {
            return new ApiResponse("Example not found", false);
        }
        exampleRepository.deleteById(id);
        return new ApiResponse("Example deleted", true);
    }
}

