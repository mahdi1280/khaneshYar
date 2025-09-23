package ir.iraniancyber.khaneshyar.controller;

import ir.iraniancyber.khaneshyar.dto.CompleteSaveDto;
import ir.iraniancyber.khaneshyar.dto.QuestionDto.QuestionDto;
import ir.iraniancyber.khaneshyar.dto.QuestionDto.QuestionSaveDto;
import ir.iraniancyber.khaneshyar.dto.SaveDto;
import ir.iraniancyber.khaneshyar.dto.optionDto.OptionDto;
import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.Question;
import ir.iraniancyber.khaneshyar.model.UserExam;
import ir.iraniancyber.khaneshyar.model.UserExamHint;
import ir.iraniancyber.khaneshyar.repository.UserExamHintRepository;
import ir.iraniancyber.khaneshyar.service.exam.ExamService;
import ir.iraniancyber.khaneshyar.service.option.OptionService;
import ir.iraniancyber.khaneshyar.service.question.QuestionService;
import ir.iraniancyber.khaneshyar.service.userExamService.UserExamService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class QuestionController {
    private final QuestionService questionService;
    private final ExamService examService;
    private final OptionService optionService;
    private final UserExamService userExamService;
    private final UserExamHintRepository userExamHintRepository;

    public QuestionController(QuestionService questionService, ExamService examService, OptionService optionService, UserExamService userExamService, UserExamHintRepository userExamHintRepository) {
        this.questionService = questionService;
        this.examService = examService;
        this.optionService = optionService;
        this.userExamService = userExamService;
        this.userExamHintRepository = userExamHintRepository;
    }

    @PostMapping
    public ResponseEntity<SaveDto> save(@Valid @RequestBody QuestionSaveDto questionSaveDto) {
        Exam exam = examService.findById(questionSaveDto.getExamId());
        Question question = questionSaveDto.convertToQuestion(exam);
        questionService.saveQuestion(question);
        return ResponseEntity.ok(new SaveDto(question.getId()));
    }

    @GetMapping
    public ResponseEntity<List<QuestionDto>> findAll() {
        List<Question> questions = questionService.findAll();
        List<QuestionDto> questionDtos = questions.stream()
                .map(question-> QuestionDto.convertToDto(question,
                        optionService.findByQuestionId(question.getId())
                                .stream().map(OptionDto::convertToDto).collect(Collectors.toList())))
                .collect(Collectors.toList());
        return ResponseEntity.ok(questionDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionDto>> findAllByExamId(@PathVariable int id) {
        Exam exam = examService.findById(id);
        List<Question> questions = questionService.findAllByExam(exam);
        List<QuestionDto> questionDtos = questions.stream()
                .map(question-> QuestionDto.convertToDto(question,
                        optionService.findByQuestionId(question.getId())
                                .stream().map(OptionDto::convertToDto).collect(Collectors.toList())))
                .collect(Collectors.toList());
        return ResponseEntity.ok(questionDtos);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/admin/{userExamId}")
    public ResponseEntity<List<QuestionDto>> findAllByUserExamId(@PathVariable int userExamId) {
        UserExam userExam = userExamService.findById(userExamId);
        List<UserExamHint> userExamHints = userExamHintRepository.findByUserExamId(userExam.getId());
        List<QuestionDto> questionDtos = userExamHints.stream()
                .map(userExamHint-> {
                    QuestionDto questionDto = QuestionDto.convertToDto(userExamHint.getQuestion(),
                            optionService.findByQuestionId(userExamHint.getQuestion().getId())
                                    .stream().map(OptionDto::convertToDto).collect(Collectors.toList()));
                    questionDto.getOptionDtos().forEach(option-> {
                        if(option.getId() == userExamHint.getOption().getId()){
                            option.setAnswered(true);
                        }
                    });
                    return questionDto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(questionDtos);
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<QuestionDto> findById(@PathVariable int id) {
        Question question = questionService.findById(id);
        List<OptionDto> collect = optionService.findByQuestionId(question.getId())
                .stream().map(OptionDto::convertToDto).collect(Collectors.toList());
        QuestionDto questionDto =QuestionDto.convertToDto(question, collect);

        return ResponseEntity.ok(questionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SaveDto> delete(@PathVariable int id) {
        questionService.delete(id);
        return ResponseEntity.ok(new SaveDto(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaveDto> update(@PathVariable int id
            , @Valid @RequestBody QuestionSaveDto questionSaveDto) {
        Exam exam = examService.findById(questionSaveDto.getExamId());
        Question question = questionSaveDto.convertToQuestion(exam);
        questionService.updateQuestion(id, question);
        return ResponseEntity.ok(new SaveDto(question.getId()));
    }

    @PostMapping("/completeSave")
    public ResponseEntity<SaveDto> completeSave(
            @Valid @RequestBody CompleteSaveDto completeSaveDto) {
        int questionId = questionService.saveCompleted(completeSaveDto);
        return ResponseEntity.ok(new SaveDto(questionId));
    }
    @PutMapping ("/completeUpdate")
    public ResponseEntity<SaveDto> updateCompleted(
            @Valid @RequestBody CompleteSaveDto completeSaveDto) {
        int questionId = questionService.updateCompleted(completeSaveDto);
        return ResponseEntity.ok(new SaveDto(questionId));
    }

}
