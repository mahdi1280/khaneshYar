package ir.iraniancyber.khaneshyar.service.submtion;

import ir.iraniancyber.khaneshyar.dto.AnswerDto;
import ir.iraniancyber.khaneshyar.dto.SubmitExamDto;
import ir.iraniancyber.khaneshyar.model.*;
import ir.iraniancyber.khaneshyar.repository.*;
import ir.iraniancyber.khaneshyar.customeExeption.RuleException;
import ir.iraniancyber.khaneshyar.service.submtion.ExamSubmissionService;

import ir.iraniancyber.khaneshyar.service.user.CustomUserDetailService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamSubmissionServiceImpl implements ExamSubmissionService {

    private final UserExamRepository userExamRepository;
    private final UserExamHintRepository userExamHintRepository;
    private final ExamRepository examRepository;
    private final CustomUserDetailService customUserDetailService;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    public ExamSubmissionServiceImpl(UserExamRepository userExamRepository,
                                     UserExamHintRepository userExamHintRepository, ExamRepository examRepository, CustomUserDetailService customUserDetailService, QuestionRepository questionRepository, OptionRepository optionRepository) {
        this.userExamRepository = userExamRepository;
        this.userExamHintRepository = userExamHintRepository;
        this.examRepository = examRepository;
        this.customUserDetailService = customUserDetailService;
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
    }

    @Override
    public void submitExam(SubmitExamDto dto) {
        if (dto.getAnswers() == null || dto.getAnswers().isEmpty()) {
            throw new RuleException("answers.empty");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getName();
        User user = customUserDetailService.findByUsername(authentication.getName());
        Exam exam = examRepository.findById(dto.getExamId())
                        .orElseThrow(()->new RuleException("exam.not.found"));
        UserExam userExam = new UserExam();
        userExam.setUser(user);
        userExam.setExam(exam);
        UserExam savedUserExam = userExamRepository.save(userExam);
        List<UserExamHint> userExamHints = dto.getAnswers().stream().map(ans -> {
            UserExamHint userExamHint = new UserExamHint();
            Question question = questionRepository.findById(ans.getQuestionId())
                    .orElseThrow(() -> new RuleException("question.not.found"));
            Option option = optionRepository.findById(ans.getOptionId())
                    .orElseThrow(() -> new RuleException("option.not.found"));
            userExamHint.setOption(option);
            userExamHint.setQuestion(question);
            userExamHint.setUserExam(savedUserExam);
            userExamHint.setHint(null);
            return userExamHint;
        }).collect(Collectors.toList());

        userExamHintRepository.saveAll(userExamHints);
    }

    @Override
    public SubmitExamDto getUserExamAnswers(int userId, int examId) {
        UserExam userExam = userExamRepository.findByUserIdAndExamId((int) userId, (int) examId);
        if (userExam == null) {
            throw new RuleException("user.exam.not.found");
        }

        List<UserExamHint> hints = userExamHintRepository.findByUserExamId(userExam.getId());

        List<AnswerDto> answers = hints.stream().map(hint -> {
            AnswerDto ans = new AnswerDto(hint.getQuestion().getId(), hint.getOption().getId());
            return ans;
        }).collect(Collectors.toList());

        SubmitExamDto dto = new SubmitExamDto( userId, answers);
        return dto;
    }
}
