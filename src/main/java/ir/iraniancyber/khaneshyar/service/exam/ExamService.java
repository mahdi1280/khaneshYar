package ir.iraniancyber.khaneshyar.service.exam;

import ir.iraniancyber.khaneshyar.model.Exam;

import java.util.List;

public interface ExamService {

    void save(Exam exam);

    List<Exam> findAll();

    void delete(int id);

    void update(int id,Exam updateExam);
}
