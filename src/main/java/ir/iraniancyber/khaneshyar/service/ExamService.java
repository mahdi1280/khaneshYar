package ir.iraniancyber.khaneshyar.service;

import ir.iraniancyber.khaneshyar.model.Exam;

import java.util.List;
import java.util.Optional;

public interface ExamService {

    void save(Exam exam);

    void update(Exam exam);

    void delete(int id);

    List<Exam> findAll();

    Optional<Exam> findById(int id);

}
