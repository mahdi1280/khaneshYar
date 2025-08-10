package ir.iraniancyber.khaneshyar.repository;

import ir.iraniancyber.khaneshyar.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

    List<Exam> findAllByDisableDateIsNull();
}
