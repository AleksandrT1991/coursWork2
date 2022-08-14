package pro.sky.courswork2.service;

import pro.sky.courswork2.entity.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions (int amount);

}

