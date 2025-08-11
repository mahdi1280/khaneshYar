package ir.iraniancyber.khaneshyar.dto;

import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.Question;

import java.time.LocalDateTime;

public class QuestionDto {
    private final int id;
    private final String title;
    private final String examName;
    private final int examId;
    private final String examLevel;
    private final LocalDateTime creationDate;
    private final LocalDateTime updatedAt;

    public QuestionDto(int id, String title, String examName, int examId, String examLevel, LocalDateTime creationDate, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.examName = examName;
        this.examId = examId;
        this.examLevel = examLevel;
        this.creationDate = creationDate;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getExamName() {
        return examName;
    }

    public int getExamId() {
        return examId;
    }

    public String getExamLevel() {
        return examLevel;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public static QuestionDto convertToDto(Question question) {
        return new QuestionDto(question.getId(),
                question.getTitle(),
                question.getExam().getName(),
                question.getExam().getId(),
                question.getExam().getLevel().getTitle(),
                question.getCreatedAt(),
                question.getUpdatedAt());
    }
}
