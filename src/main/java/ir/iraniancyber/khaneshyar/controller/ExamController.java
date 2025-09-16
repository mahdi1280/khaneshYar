package ir.iraniancyber.khaneshyar.controller;

import ir.iraniancyber.khaneshyar.customeExeption.RuleException;
import ir.iraniancyber.khaneshyar.dto.ExamDto.ExamDto;
import ir.iraniancyber.khaneshyar.dto.ExamDto.ExamSaveDto;
import ir.iraniancyber.khaneshyar.dto.ExamUserResponseDto;
import ir.iraniancyber.khaneshyar.dto.SaveDto;
import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.Level;
import ir.iraniancyber.khaneshyar.service.exam.ExamService;
import ir.iraniancyber.khaneshyar.service.level.LevelService;
import ir.iraniancyber.khaneshyar.service.userExamService.UserExamService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exams")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ExamController {

    private final ExamService examService;
    private final LevelService levelService;
    private final UserExamService userExamService;

    public ExamController(ExamService examService, LevelService levelService, UserExamService userExamService) {
        this.examService = examService;
        this.levelService = levelService;
        this.userExamService = userExamService;
    }

    @PostMapping
    public ResponseEntity<SaveDto> save(@Valid @RequestBody ExamSaveDto examSaveDto) {
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

    //    @GetMapping
    // todo api for get exam

    @GetMapping("/{id}")
    public ResponseEntity<ExamDto> findById(@PathVariable int id) {
        Exam exam = examService.findById(id);
        ExamDto examDto = ExamDto.convertToDto(exam);
        return ResponseEntity.ok(examDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SaveDto> delete(@PathVariable int id) {
        examService.delete(id);
        return ResponseEntity.ok(new SaveDto(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaveDto> update(@PathVariable int id
            , @Valid @RequestBody ExamSaveDto examSaveDto) {
        Level level = levelService.findByCode(examSaveDto.getLevel())
                .orElseThrow(() ->
                        new RuleException("level.not.found"));
        Exam exam = examSaveDto.convertToExam(level);
        examService.update(id, exam);
        return ResponseEntity.ok(new SaveDto(exam.getId()));

    }

    @GetMapping("/get-exam")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<ExamUserResponseDto>> getExam() {
        return ResponseEntity.ok(userExamService.getAll());
    }
}
