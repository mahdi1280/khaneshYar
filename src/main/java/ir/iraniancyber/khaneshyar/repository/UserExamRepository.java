package ir.iraniancyber.khaneshyar.repository;

import ir.iraniancyber.khaneshyar.model.UserExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExamRepository extends JpaRepository<UserExam, Integer> {
    List<UserExam> findByUserId(int userId);

    UserExam findByUserIdAndExamId(int userId, int examId);


}
