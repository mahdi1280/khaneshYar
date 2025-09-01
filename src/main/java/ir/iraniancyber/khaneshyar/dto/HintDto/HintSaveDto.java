package ir.iraniancyber.khaneshyar.dto.HintDto;

import ir.iraniancyber.khaneshyar.model.Hint;
import ir.iraniancyber.khaneshyar.model.Question;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HintSaveDto {

    private final String title;
    private final Integer level;
    private final int questionId;

    public HintSaveDto(String title, Integer level, int questionId) {
        this.title = title;
        this.level = level;
        this.questionId = questionId;
    }

    @NotBlank(message = "hint.title.null")
    public String getTitle() {
        return title;
    }

    @NotNull(message = "hint.level.null")
    public Integer getLevel() {
        return level;
    }

    public int getQuestionId() {
        return questionId;
    }

    public Hint convertToHint(Question question) {
        Hint hint = new Hint();
        hint.setTitle(title);
        hint.setLevel(level);
        hint.setQuestion(question);
        return hint;
    }
}

