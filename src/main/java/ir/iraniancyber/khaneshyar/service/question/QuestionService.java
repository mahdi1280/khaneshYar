package ir.iraniancyber.khaneshyar.service.question;

import ir.iraniancyber.khaneshyar.model.Question;

import java.util.List;

public interface QuestionService {
    void saveQuestion(Question question);

    void update(int id, Question question);

    void delete(int id);

    List<Question> findAll();

    Question findById(int id);
}
