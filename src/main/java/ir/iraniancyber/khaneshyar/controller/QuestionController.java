package ir.iraniancyber.khaneshyar.controller;

import ir.iraniancyber.khaneshyar.dto.CompleteSaveDto;
import ir.iraniancyber.khaneshyar.dto.QuestionDto.QuestionDto;
import ir.iraniancyber.khaneshyar.dto.QuestionDto.QuestionSaveDto;
import ir.iraniancyber.khaneshyar.dto.SaveDto;
import ir.iraniancyber.khaneshyar.dto.optionDto.OptionDto;
import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.Question;
import ir.iraniancyber.khaneshyar.service.exam.ExamService;
import ir.iraniancyber.khaneshyar.service.option.OptionService;
import ir.iraniancyber.khaneshyar.service.question.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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

    public QuestionController(QuestionService questionService, ExamService examService, OptionService optionService) {
        this.questionService = questionService;
        this.examService = examService;
        this.optionService = optionService;
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
