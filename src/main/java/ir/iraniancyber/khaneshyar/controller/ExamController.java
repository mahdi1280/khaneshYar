package ir.iraniancyber.khaneshyar.controller;

import ir.iraniancyber.khaneshyar.customeExeption.RuleException;
import ir.iraniancyber.khaneshyar.dto.ExamDto;
import ir.iraniancyber.khaneshyar.dto.ExamSaveDto;
import ir.iraniancyber.khaneshyar.dto.SaveDto;
import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.Level;
import ir.iraniancyber.khaneshyar.service.exam.ExamService;
import ir.iraniancyber.khaneshyar.service.level.LevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exams")
public class ExamController {

    private final ExamService examService;
    private final LevelService levelService;

    public ExamController(ExamService examService, LevelService levelService) {
        this.examService = examService;
        this.levelService = levelService;
    }

    @PostMapping
    public ResponseEntity<SaveDto> save(@RequestBody ExamSaveDto examSaveDto) {
        Level level = levelService.findByCode(examSaveDto.getLevel())
                .orElseThrow(() ->
                        new RuleException("level.not.found"));
        Exam exam = examSaveDto.convertToExam(level);
        examService.save(exam);
        return ResponseEntity.ok(new SaveDto(exam.getId()));
    }

    @GetMapping
    public ResponseEntity<List<ExamDto>> findAll() {
        List<Exam> exams = examService.findAll();
        List<ExamDto> examDtos = exams.stream()
                .map(ExamDto::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(examDtos);
    }
//
//    @GetMapping
//    //findAll
//
//    @PutMapping
//    //update
//
//    @DeleteMapping
//    //delete

}
