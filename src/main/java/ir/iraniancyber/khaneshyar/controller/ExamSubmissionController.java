package ir.iraniancyber.khaneshyar.controller;

import ir.iraniancyber.khaneshyar.dto.SaveDto;
import ir.iraniancyber.khaneshyar.dto.SubmitExamDto;
import ir.iraniancyber.khaneshyar.service.submtion.ExamSubmissionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam-submission")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ExamSubmissionController {

    private final ExamSubmissionService examSubmissionService;

    public ExamSubmissionController(ExamSubmissionService examSubmissionService) {
        this.examSubmissionService = examSubmissionService;
    }

    @PostMapping
    public ResponseEntity<SaveDto> submitExam(@Valid @RequestBody SubmitExamDto dto) {
        examSubmissionService.submitExam(dto);
        return ResponseEntity.ok(new SaveDto(dto.getExamId()));
    }

    @GetMapping("/user/{userId}/exam/{examId}")
    public ResponseEntity<SubmitExamDto> getUserExamAnswers(@PathVariable int userId,
                                                            @PathVariable int examId) {
        SubmitExamDto dto = examSubmissionService.getUserExamAnswers(userId, examId);
        return ResponseEntity.ok(dto);
    }
}
