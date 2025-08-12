package ir.iraniancyber.khaneshyar.dto.optionDto;

import ir.iraniancyber.khaneshyar.model.Option;
import ir.iraniancyber.khaneshyar.model.Question;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OptionSaveDto {
    private final String title;
    private final boolean correct;
    private final int questionId;


    @NotBlank(message = "option.save.dto.title.null")
    public String getTitle() {
        return title;
    }

    @NotNull(message = "option.save.dto.correct.null")
    public boolean isCorrect() {
        return correct;
    }

    @NotNull(message = "option.save.dto.questionId.null")
    public int getQuestionId() {
        return questionId;
    }

    public OptionSaveDto(String title, boolean correct, int questionId) {
        this.title = title;
        this.correct = correct;
        this.questionId = questionId;
    }

    public Option convertToOption(Question question) {
        Option option = new Option();
        option.setTitle(title);
        option.setCorrect(correct);
        option.setQuestion(question);
        return option;
    }
}
