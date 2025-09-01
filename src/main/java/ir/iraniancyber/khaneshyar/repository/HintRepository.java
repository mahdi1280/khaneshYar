package ir.iraniancyber.khaneshyar.repository;

import ir.iraniancyber.khaneshyar.model.Hint;
import ir.iraniancyber.khaneshyar.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HintRepository extends JpaRepository<Hint, Integer> {
    List<Hint> findByQuestionIdAndDisableDateIsNull(int QuestionId);

}
