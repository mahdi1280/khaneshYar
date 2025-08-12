package ir.iraniancyber.khaneshyar.dto.optionDto;

import ir.iraniancyber.khaneshyar.model.Option;

import java.time.LocalDateTime;

public class OptionDto {
    private final int id;
    private final String title;
    private final int examId;
    private final String examName;
    private final int questionId;
    private final String questionTitle;
    private final boolean correct;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getExamId() {
        return examId;
    }

    public String getExamName() {
        return examName;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public boolean isCorrect() {
        return correct;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public OptionDto(int id, String title, int examId, String examTitle, int questionId, String questionTitle, boolean correct, LocalDateTime createdAt, LocalDateTime updqtedAt) {
        this.id = id;
        this.title = title;
        this.examId = examId;
        this.examName = examTitle;
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.correct = correct;
        this.createdAt = createdAt;
        this.updatedAt = updqtedAt;
    }
    public static OptionDto convertToDto(Option option) {
        return new OptionDto(
               option.getId(),
                option.getTitle(),
                option.getQuestion().getExam().getId(),
                option.getQuestion().getExam().getName(),
                option.getQuestion().getId(),
                option.getQuestion().getTitle(),
                option.isCorrect(),
                option.getCreatedAt(),
                option.getUpdatedAt());
    }
}
