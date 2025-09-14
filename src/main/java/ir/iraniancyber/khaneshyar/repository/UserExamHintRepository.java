package ir.iraniancyber.khaneshyar.repository;

import ir.iraniancyber.khaneshyar.model.UserExamHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExamHintRepository extends JpaRepository<UserExamHint, Integer> {
    List<UserExamHint> findByUserExamId(int userId);

    UserExamHint findByUserExamIdAndQuestionId(int userId, int questionId);
}
