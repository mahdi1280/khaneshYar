package ir.iraniancyber.khaneshyar.service.userExamService;

import ir.iraniancyber.khaneshyar.dto.ExamUserResponseDto;
import ir.iraniancyber.khaneshyar.model.UserExam;

import java.util.List;

public interface UserExamService {

    List<ExamUserResponseDto> getAll(int userId);

    UserExam findById(int id);
}
