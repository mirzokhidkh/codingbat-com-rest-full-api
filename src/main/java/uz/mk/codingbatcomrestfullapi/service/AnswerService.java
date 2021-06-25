package uz.mk.codingbatcomrestfullapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.mk.codingbatcomrestfullapi.entity.Answer;
import uz.mk.codingbatcomrestfullapi.entity.Question;
import uz.mk.codingbatcomrestfullapi.entity.User;
import uz.mk.codingbatcomrestfullapi.payload.ApiResponse;
import uz.mk.codingbatcomrestfullapi.payload.AnswerDto;
import uz.mk.codingbatcomrestfullapi.repository.AnswerRepository;
import uz.mk.codingbatcomrestfullapi.repository.QuestionRepository;
import uz.mk.codingbatcomrestfullapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    public ApiResponse add(AnswerDto answerDto) {
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }

        Optional<Question> optionalQuestion = questionRepository.findById(answerDto.getQuestionId());
        if (optionalQuestion.isEmpty()) {
            return new ApiResponse("Question not found", false);
        }

        boolean exists = answerRepository.existsByTextAndUserId(answerDto.getText(), answerDto.getUserId());
        if (exists) {
            return new ApiResponse("A answer with such a text and User ID already exists", false);
        }


        Answer answer = new Answer();
        answer.setText(answerDto.getText());
        answer.setQuestion(optionalQuestion.get());
        answer.setUser(optionalUser.get());
        answer.setCorrect(answerDto.isCorrect());
        answerRepository.save(answer);
        return new ApiResponse("Answer saved", true);
    }

    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    public Answer getOneById(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isEmpty()) {
            return new Answer();
        }
        return optionalAnswer.get();
    }

    public ApiResponse edit(Integer id, AnswerDto answerDto) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isEmpty()) {
            return new ApiResponse("Answer not found", false);
        }

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }

        Optional<Question> optionalQuestion = questionRepository.findById(answerDto.getQuestionId());
        if (optionalQuestion.isEmpty()) {
            return new ApiResponse("Question not found", false);
        }

        boolean exists = answerRepository.existsByTextAndUserIdAndIdNot(answerDto.getText(), answerDto.getUserId(),id);
        if (exists) {
            return new ApiResponse("A answer with such a text and User ID already exists", false);
        }

        Answer answer = optionalAnswer.get();
        answer.setText(answerDto.getText());
        answer.setQuestion(optionalQuestion.get());
        answer.setUser(optionalUser.get());
        answer.setCorrect(answerDto.isCorrect());
        answerRepository.save(answer);
        return new ApiResponse("Answer edited", true);
    }

    public ApiResponse deleteById(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isEmpty()) {
            return new ApiResponse("Answer not found", false);
        }
        answerRepository.deleteById(id);
        return new ApiResponse("Answer deleted", true);
    }
}

