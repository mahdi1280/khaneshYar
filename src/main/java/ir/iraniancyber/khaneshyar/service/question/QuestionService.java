package ir.iraniancyber.khaneshyar.service.question;

import ir.iraniancyber.khaneshyar.dto.CompleteSaveDto;
import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.Question;

import java.util.List;

public interface QuestionService {
    void saveQuestion(Question question);

    void updateQuestion(int id, Question question);

    void delete(int id);

    List<Question> findAll();

    Question findById(int id);

    int saveCompleted(CompleteSaveDto completeSaveDto);

    int updateCompleted(CompleteSaveDto completeSaveDto);


    List<Question> findAllByExam(Exam exam);
}
