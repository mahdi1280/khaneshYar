package ir.iraniancyber.khaneshyar.dto;

import ir.iraniancyber.khaneshyar.dto.HintDto.HintSaveDto;
import ir.iraniancyber.khaneshyar.dto.optionDto.OptionSaveDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CompleteSaveDto {

    private final String title;
    private final Integer examId;
    private final List<OptionSaveDto> optionSaveDtos;
    private final List<HintSaveDto> hintSaveDtos;

    public CompleteSaveDto(String title, Integer examId, List<OptionSaveDto> optionSaveDtos, List<HintSaveDto> hintSaveDtos) {
        this.title = title;
        this.examId = examId;
        this.optionSaveDtos = optionSaveDtos;
        this.hintSaveDtos = hintSaveDtos;
    }

    @NotBlank(message = "title.null")
    public String getTitle() {
        return title;
    }

    public List<OptionSaveDto> getOptionSaveDtos() {
        return optionSaveDtos;
    }

    public List<HintSaveDto> getHintSaveDtos() {
        return hintSaveDtos;
    }

    @NotNull(message = "exam.id.null")
    public Integer getExamId() {
        return examId;
    }
}
