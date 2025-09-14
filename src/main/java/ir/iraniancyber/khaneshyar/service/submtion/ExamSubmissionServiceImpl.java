package ir.iraniancyber.khaneshyar.service.submtion;

import ir.iraniancyber.khaneshyar.dto.AnswerDto;
import ir.iraniancyber.khaneshyar.dto.SubmitExamDto;
import ir.iraniancyber.khaneshyar.model.*;
import ir.iraniancyber.khaneshyar.repository.UserExamHintRepository;
import ir.iraniancyber.khaneshyar.repository.UserExamRepository;
import ir.iraniancyber.khaneshyar.customeExeption.RuleException;
import ir.iraniancyber.khaneshyar.service.submtion.ExamSubmissionService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamSubmissionServiceImpl implements ExamSubmissionService {

    private final UserExamRepository userExamRepository;
    private final UserExamHintRepository userExamHintRepository;

    public ExamSubmissionServiceImpl(UserExamRepository userExamRepository,
                                     UserExamHintRepository userExamHintRepository) {
        this.userExamRepository = userExamRepository;
        this.userExamHintRepository = userExamHintRepository;
    }

    @Override

    public void submitExam(SubmitExamDto dto) {
        if (dto.getAnswers() == null || dto.getAnswers().isEmpty()) {
            throw new RuleException("answers.empty");
        }


        UserExam existing = userExamRepository.findByUserIdAndExamId((int) dto.getUserId(), (int) dto.getExamId());
        if (existing != null) {
            throw new RuleException("exam.already.submitted");
        }


        UserExam userExam = new UserExam();
        User user = new User();
        user.setId(dto.getUserId());
        Exam exam = new Exam();
        exam.setId(dto.getExamId());

        userExam.setUser(user);
        userExam.setExam(exam);

        UserExam savedUserExam = userExamRepository.save(userExam);


        List<UserExamHint> hints = dto.getAnswers().stream().map(ans -> {
            UserExamHint hint = new UserExamHint();
            hint.setUserExam(savedUserExam);

            Question q = new Question();
            q.setId(ans.getQuestionId());
            hint.setQuestion(q);

            Option o = new Option();
            o.setId(ans.getOptionId());
            hint.setOption(o);

            return hint;
        }).collect(Collectors.toList());

        userExamHintRepository.saveAll(hints);
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

        SubmitExamDto dto = new SubmitExamDto((int) userId, (int) examId, answers);
        return dto;
    }
}
