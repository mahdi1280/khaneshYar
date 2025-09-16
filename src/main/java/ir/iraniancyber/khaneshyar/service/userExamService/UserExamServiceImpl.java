package ir.iraniancyber.khaneshyar.service.userExamService;

import ir.iraniancyber.khaneshyar.dto.ExamUserResponseDto;
import ir.iraniancyber.khaneshyar.model.User;
import ir.iraniancyber.khaneshyar.model.UserExam;
import ir.iraniancyber.khaneshyar.repository.UserExamRepository;
import ir.iraniancyber.khaneshyar.service.user.CustomUserDetailService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserExamServiceImpl implements UserExamService {

    private final UserExamRepository userExamRepository;
    private final CustomUserDetailService customUserDetailService;

    public UserExamServiceImpl(UserExamRepository userExamRepository, CustomUserDetailService customUserDetailService) {
        this.userExamRepository = userExamRepository;
        this.customUserDetailService = customUserDetailService;
    }

    @Override
    public List<ExamUserResponseDto> getAll(int userId) {
        List<UserExam> userExams = userExamRepository.findByUserId(userId);
        return userExams.stream().map(exam->{
            ExamUserResponseDto userResponseDto = new ExamUserResponseDto();
            userResponseDto.setExamName(exam.getExam().getName());
            userResponseDto.setCreationDate(exam.getCreatedAt());
            return userResponseDto;
        }).collect(Collectors.toList());
    }
}
