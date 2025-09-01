package ir.iraniancyber.khaneshyar.controller;

import ir.iraniancyber.khaneshyar.dto.HintDto.HintDto;
import ir.iraniancyber.khaneshyar.dto.HintDto.HintSaveDto;
import ir.iraniancyber.khaneshyar.dto.SaveDto;
import ir.iraniancyber.khaneshyar.dto.optionDto.OptionDto;
import ir.iraniancyber.khaneshyar.model.Hint;
import ir.iraniancyber.khaneshyar.model.Option;
import ir.iraniancyber.khaneshyar.model.Question;
import ir.iraniancyber.khaneshyar.service.Hint.HintService;
import ir.iraniancyber.khaneshyar.service.question.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hint")
public class HintController {
    private final HintService hintService;
    private final QuestionService questionService;

    public HintController(HintService hintService, QuestionService questionService) {
        this.hintService = hintService;
        this.questionService = questionService;
    }

    @PostMapping
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

    @GetMapping("/{questionId}")
    public ResponseEntity<List<HintDto>> findByQuestion(@PathVariable int questionId) {
        List<Hint> hints = hintService.findByQuestionId(questionId);
        List<HintDto> hintDtos=hints.stream()
                .map(HintDto::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(hintDtos);
    }
}
