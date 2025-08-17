package ir.iraniancyber.khaneshyar.controller;

import ir.iraniancyber.khaneshyar.dto.SaveDto;
import ir.iraniancyber.khaneshyar.dto.optionDto.OptionDto;
import ir.iraniancyber.khaneshyar.dto.optionDto.OptionSaveDto;
import ir.iraniancyber.khaneshyar.model.Option;
import ir.iraniancyber.khaneshyar.model.Question;
import ir.iraniancyber.khaneshyar.service.option.OptionService;
import ir.iraniancyber.khaneshyar.service.question.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/options")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class OptionController {
    private final OptionService optionService;
    private final QuestionService questionService;

    public OptionController(OptionService optionService, QuestionService questionService) {
        this.optionService = optionService;
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<SaveDto> save(@Valid @RequestBody OptionSaveDto optionSaveDto) {
        Question question = questionService.findById(optionSaveDto.getQuestionId());
        Option option = optionSaveDto.convertToOption(question);
        optionService.Save(option);
        return ResponseEntity.ok(new SaveDto(option.getId()));
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<List<OptionDto>> findByQuestion(@PathVariable int questionId) {
        List<Option> options = optionService.findByQuestionId(questionId);
        List<OptionDto> optionDtos = options.stream()
                .map(OptionDto::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(optionDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SaveDto> delete(@PathVariable int id) {
        optionService.delete(id);
        return ResponseEntity.ok(new SaveDto(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaveDto> update(@PathVariable int id
            , @Valid @RequestBody OptionSaveDto optionSaveDto) {
        Question question = questionService.findById(id);
        Option option = optionSaveDto.convertToOption(question);
        optionService.update(id, option);
        return ResponseEntity.ok(new SaveDto(option.getId()));
    }
}










