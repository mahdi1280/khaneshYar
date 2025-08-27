package ir.iraniancyber.khaneshyar.repository;

import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findAllByDisableDateIsNull();

    List<Question> findAllByExamAndDisableDateIsNull(Exam exam);
}
