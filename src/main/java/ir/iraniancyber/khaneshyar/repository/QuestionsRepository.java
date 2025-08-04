package ir.iraniancyber.khaneshyar.repository;

import ir.iraniancyber.khaneshyar.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Question, Integer> {
}
