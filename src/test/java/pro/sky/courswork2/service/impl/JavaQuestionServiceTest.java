package pro.sky.courswork2.service.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.courswork2.entity.Question;
import pro.sky.courswork2.excetions.NotFoundException;
import pro.sky.courswork2.excetions.RepeatQuestion;
import pro.sky.courswork2.service.QuestionService;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class JavaQuestionServiceTest {
    private final QuestionService questionService = new JavaQuestionService();

    @ParameterizedTest
    @MethodSource("question1")

    public void addOneTest(Question question) {
        questionService.add(question);
        assertThatExceptionOfType(RepeatQuestion.class).isThrownBy(() -> questionService.add(question));
        assertThat(questionService.getAll()).containsExactlyInAnyOrder(question);
    }

    @ParameterizedTest
    @MethodSource("question2")
    public void addTwoTest(String question, String answer) {
        questionService.add(question, answer);
        assertThatExceptionOfType(RepeatQuestion.class).isThrownBy(() -> questionService.add(question, answer));
        assertThat(questionService.getAll()).containsExactlyInAnyOrder(new Question(question, answer));
    }

    @ParameterizedTest
    @MethodSource("question1")
    public void removeTest(Question question) {
        questionService.add(question);
        questionService.remove(question);
        assertThat(questionService.getAll()).isEmpty();
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> questionService.remove(question));
    }

    @ParameterizedTest
    @MethodSource("questions")
    public void getRandomTest(Set <Question> questions) {
        questions.forEach(questionService::add );
        assertThat(questionService.getAll()).hasSize(questions.size());
        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());
    }
    public static Stream<Arguments> question1(){
        return Stream.of(Arguments.of(new Question("Question", "Answer")));
    }
    public static Stream<Arguments> question2(){
        return Stream.of(Arguments.of(new Question("Question", "Answer")));
    }
    public static Stream<Arguments> questions() {
        return Stream.of(Arguments.of(Set.of(new Question("Question1", "Answer1"),
                                              new Question("Question2", "Answer2"),
                                              new Question("Question3", "Answer3"))));
    }
}

