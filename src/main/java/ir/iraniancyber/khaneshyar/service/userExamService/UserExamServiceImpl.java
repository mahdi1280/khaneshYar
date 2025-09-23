package ir.iraniancyber.khaneshyar.service.userExamService;

import ir.iraniancyber.khaneshyar.customeExeption.RuleException;
import ir.iraniancyber.khaneshyar.dto.ExamUserResponseDto;
import ir.iraniancyber.khaneshyar.model.User;
import ir.iraniancyber.khaneshyar.model.UserExam;
import ir.iraniancyber.khaneshyar.model.UserExamHint;
import ir.iraniancyber.khaneshyar.repository.UserExamHintRepository;
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
    private final UserExamHintRepository userExamHintRepository;

    public UserExamServiceImpl(UserExamRepository userExamRepository, CustomUserDetailService customUserDetailService, UserExamHintRepository userExamHintRepository) {
        this.userExamRepository = userExamRepository;
        this.customUserDetailService = customUserDetailService;
        this.userExamHintRepository = userExamHintRepository;
    }

    @Override
    public List<ExamUserResponseDto> getAll(int userId) {
        List<UserExam> userExams = userExamRepository.findByUserId(userId);
        return userExams.stream().map(exam->{
            ExamUserResponseDto userResponseDto = new ExamUserResponseDto();
            userResponseDto.setExamId(exam.getExam().getId());
            userResponseDto.setUserExamId(exam.getId());
            userResponseDto.setExamName(exam.getExam().getName());
            userResponseDto.setCreationDate(exam.getCreatedAt());
            List<UserExamHint> userExamHints = userExamHintRepository.findByUserExamId(exam.getId());
            userResponseDto.setScore(getScore(userExamHints));
            return userResponseDto;
        }).collect(Collectors.toList());
    }

    @Override
    public UserExam findById(int id) {
        return userExamRepository.findById(id)
                .orElseThrow(()->new RuleException("user.exam.not.found"));
    }

    private float getScore( List<UserExamHint> userExamHints) {
        float score = 0;
        for (int i = 0; i < userExamHints.size(); i++) {
            if(userExamHints.get(i).getOption().isCorrect()) {
                score += 1;
            }
        }
        return score/userExamHints.size() * 20;
    }
}
