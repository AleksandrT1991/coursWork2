package pro.sky.courswork2.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.courswork2.entity.Question;
import pro.sky.courswork2.excetions.NotFoundException;
import pro.sky.courswork2.excetions.RepeatQuestion;
import pro.sky.courswork2.service.QuestionService;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions;

    private final Random random;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
        this.random = new Random();
    }
    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        if (questions.contains(q)) {
            throw new RepeatQuestion();
        }
        questions.add(q);
        return q;
    }
    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new RepeatQuestion();
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)) {
            throw new NotFoundException();
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return (questions);
     }

    @Override
    public Question getRandomQuestion() {
        return new ArrayList<>(questions).get(random.nextInt(questions.size()));
    }
}

//
