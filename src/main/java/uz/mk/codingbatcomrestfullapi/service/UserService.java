package uz.mk.codingbatcomrestfullapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.mk.codingbatcomrestfullapi.entity.User;
import uz.mk.codingbatcomrestfullapi.payload.ApiResponse;
import uz.mk.codingbatcomrestfullapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ApiResponse add(User user) {
        boolean exists = userRepository.existsByEmail(user.getEmail());
        if (exists) {
            return new ApiResponse("A User with such a email already exists", false);
        }
        userRepository.save(user);
        return new ApiResponse("User saved", true);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getOneById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new User();
        }
        return optionalUser.get();
    }

    public ApiResponse edit(Integer id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }

        boolean exists = userRepository.existsByEmailAndIdNot(user.getEmail(), id);
        if (exists) {
            return new ApiResponse("A User with such a email already exists", false);
        }

        User editingUser = optionalUser.get();
        editingUser.setEmail(user.getEmail());
        editingUser.setPassword(user.getPassword());
        userRepository.save(editingUser);
        return new ApiResponse("User edited", true);
    }

    public ApiResponse deleteById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }
        userRepository.deleteById(id);
        return new ApiResponse("User deleted", true);
    }
}

