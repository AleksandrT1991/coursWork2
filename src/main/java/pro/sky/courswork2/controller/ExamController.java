package pro.sky.courswork2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import pro.sky.courswork2.entity.Question;
import pro.sky.courswork2.service.ExaminerService;

import java.util.Collection;

public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public Collection<Question> getQuestions(int amount) {
        return examinerService.getQuestions(amount);
    }
}
//