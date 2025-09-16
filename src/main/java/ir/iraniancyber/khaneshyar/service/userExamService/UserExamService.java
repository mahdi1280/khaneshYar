package ir.iraniancyber.khaneshyar.service.userExamService;

import ir.iraniancyber.khaneshyar.dto.ExamUserResponseDto;

import java.util.List;

public interface UserExamService {

    List<ExamUserResponseDto> getAll(int userId);
}
