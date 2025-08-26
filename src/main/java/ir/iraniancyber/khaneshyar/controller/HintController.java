package ir.iraniancyber.khaneshyar.controller;

import ir.iraniancyber.khaneshyar.dto.HintSaveDto;
import ir.iraniancyber.khaneshyar.dto.QuestionDto.QuestionSaveDto;
import ir.iraniancyber.khaneshyar.dto.SaveDto;
import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.Hint;
import ir.iraniancyber.khaneshyar.model.Question;
import ir.iraniancyber.khaneshyar.service.Hint.HintService;
import ir.iraniancyber.khaneshyar.service.question.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hint")
public class HintController {
    private final HintService hintService;
    private final QuestionService questionService;

    public HintController(HintService hintService, QuestionService questionService) {
        this.hintService = hintService;
        this.questionService = questionService;
    }

    @PostMapping()
    public ResponseEntity<SaveDto> createHint(@RequestBody @Valid HintSaveDto hintSaveDto) {
        Question question = questionService.findById(hintSaveDto.getQuestionId());
        Hint hint = hintSaveDto.convertToHint(question);
        hintService.save(hint);
        return ResponseEntity.ok(new SaveDto(hint.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SaveDto> delete(@PathVariable int id) {
        hintService.delete(id);
        return ResponseEntity.ok(new SaveDto(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaveDto> update(@PathVariable int id
            , @Valid @RequestBody HintSaveDto hintSaveDto) {
        Question question = questionService.findById(hintSaveDto.getQuestionId());
        Hint hint = hintSaveDto.convertToHint(question);
        hintService.update(id, hint);
        return ResponseEntity.ok(new SaveDto(hint.getId()));
    }
}
