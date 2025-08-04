package ir.iraniancyber.khaneshyar.controller;

import ir.iraniancyber.khaneshyar.dto.ExamDto;
import ir.iraniancyber.khaneshyar.dto.ExamSaveDto;
import ir.iraniancyber.khaneshyar.dto.SaveResponseDto;
import ir.iraniancyber.khaneshyar.exeption.RuleException;
import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.ExamLevel;
import ir.iraniancyber.khaneshyar.service.ExamLevelService;
import ir.iraniancyber.khaneshyar.service.ExamService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exams")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ExamController {


    private final ExamService examService;
    private final ExamLevelService examLevelService;

    public ExamController(ExamService examService, ExamLevelService examLevelService) {
        this.examService = examService;
        this.examLevelService = examLevelService;
    }

    @PostMapping
    public ResponseEntity<SaveResponseDto> saveResponseDtoResponseEntity(@RequestBody @Valid ExamSaveDto examSaveDto) {
        ExamLevel examLevel = examLevelService.findById(examSaveDto.getLevel())
                .orElseThrow(() -> new RuleException("exam.level.not.found"));
        Exam exam = examSaveDto.convertToExam(examLevel);
        examService.save(exam);
        return ResponseEntity.ok(new SaveResponseDto(exam.getId()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        examService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody @Valid ExamSaveDto examSaveDto) {
        ExamLevel examLevel = examLevelService.findById(examSaveDto.getLevel())
                .orElseThrow(() -> new RuleException("exam.level.not.found"));
        Exam exam = examService.findById(id)
                .orElseThrow(() -> new RuleException("exam.not.found"));
        exam.setName(examSaveDto.getName());
        exam.setDescription(examSaveDto.getDescription());
        exam.setLevel(examLevel);
        examService.update(exam);
    }

    @GetMapping
    public ResponseEntity<List<ExamDto>> findAll() {
        List<ExamDto> examDtos = examService.findAll()
                .stream().map(ExamDto::convertExam)
                .collect(Collectors.toList());
        return ResponseEntity.ok(examDtos);
    }
}
