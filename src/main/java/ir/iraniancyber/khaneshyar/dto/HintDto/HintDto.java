package ir.iraniancyber.khaneshyar.dto.HintDto;

import ir.iraniancyber.khaneshyar.dto.optionDto.OptionDto;
import ir.iraniancyber.khaneshyar.model.Hint;
import ir.iraniancyber.khaneshyar.model.Option;

import java.time.LocalDateTime;

public class HintDto {
    private final int id;
    private final String title;
    private final int level;
    private final String questionTitle;
    private final int questionId;
    private final String examName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getLevel() {
        return level;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getExamName() {
        return examName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public HintDto(int id, String title, int level, String questionTitle, int questionId, String examName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.level = level;
        this.questionTitle = questionTitle;
        this.questionId = questionId;
        this.examName = examName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static HintDto convertToDto(Hint hint) {
        return new HintDto(hint.getId(), hint.getTitle(), hint.getLevel(), hint.getQuestion().getTitle(), hint.getQuestion().getId(), hint.getQuestion().getExam().getName(), hint.getCreatedAt(), hint.getUpdatedAt());
    }


}
