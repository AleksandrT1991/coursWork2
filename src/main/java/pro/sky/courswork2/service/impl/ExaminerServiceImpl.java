package pro.sky.courswork2.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.courswork2.entity.Question;
import pro.sky.courswork2.excetions.MaxAnswerQuestionsException;
import pro.sky.courswork2.service.ExaminerService;
import pro.sky.courswork2.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0 || amount > questionService.getAll().size()) {
            throw new MaxAnswerQuestionsException();
        }
        Set<Question> result = new HashSet<>(amount);
        while (result.size() < amount) {
            result.add((questionService.getRandomQuestion()));
        }
        return result;
    }
}
