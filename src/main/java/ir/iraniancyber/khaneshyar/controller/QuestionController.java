package ir.iraniancyber.khaneshyar.controller;

import ir.iraniancyber.khaneshyar.dto.*;
import ir.iraniancyber.khaneshyar.dto.QuestionDto.QuestionDto;
import ir.iraniancyber.khaneshyar.dto.QuestionDto.QuestionSaveDto;
import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.Question;
import ir.iraniancyber.khaneshyar.service.exam.ExamService;
import ir.iraniancyber.khaneshyar.service.question.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final ExamService examService;

    public QuestionController(QuestionService questionService, ExamService examService) {
        this.questionService = questionService;
        this.examService = examService;
    }
    @PostMapping
    public ResponseEntity<SaveDto> save(@Valid @RequestBody QuestionSaveDto questionSaveDto){
        Exam exam= examService.findById(questionSaveDto.getExam_id());
        Question question=questionSaveDto.convertToQuestion(exam);
        questionService.SaveQuestion(question);
        return ResponseEntity.ok(new SaveDto(question.getId()));
    }
    @GetMapping
    public ResponseEntity<List<QuestionDto>> findAll() {
        List<Question> questions = questionService.findAll();
        List<QuestionDto> questionDtos = questions.stream()
                .map(QuestionDto::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(questionDtos);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<SaveDto> delete(@PathVariable int id) {
        questionService.delete(id);
        return ResponseEntity.ok(new SaveDto(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<SaveDto> update(@PathVariable int id,@Valid @RequestBody QuestionSaveDto questionSaveDto){
        Exam exam = examService.findById(questionSaveDto.getExam_id());
        Question question= questionSaveDto.convertToQuestion(exam);
        questionService.update(id,question);
        return ResponseEntity.ok(new SaveDto(question.getId()));

    }
}
