package uz.mk.codingbatcomrestfullapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mk.codingbatcomrestfullapi.entity.Subject;
import uz.mk.codingbatcomrestfullapi.payload.ApiResponse;
import uz.mk.codingbatcomrestfullapi.service.SubjectService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @PostMapping
    public HttpEntity<?> add(@RequestBody Subject subject) {
        ApiResponse response = subjectService.add(subject);
        return ResponseEntity
                .status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT)
                .body(response);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        List<Subject> subjects = subjectService.getAll();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        Subject subject = subjectService.getOneById(id);
        return ResponseEntity.ok(subject);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody Subject subject) {
        ApiResponse response = subjectService.edit(id, subject);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Integer id) {
        ApiResponse response = subjectService.deleteById(id);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(response);
    }
}
