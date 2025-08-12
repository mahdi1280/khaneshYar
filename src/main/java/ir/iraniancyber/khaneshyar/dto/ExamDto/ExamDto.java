package ir.iraniancyber.khaneshyar.dto.ExamDto;

import ir.iraniancyber.khaneshyar.model.Exam;

import java.time.LocalDateTime;

public class ExamDto {

    private final int id;
    private final String name;
    private final String description;
    private final String levelTitle;
    private final String levelCode;
    private final int levelId;
    private final LocalDateTime creationDate;
    private final LocalDateTime updatedAt;

    public ExamDto(int id, String name, String description, String levelTitle, String levelCode, int levelId, LocalDateTime creationDate, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.levelTitle = levelTitle;
        this.levelCode = levelCode;
        this.levelId = levelId;
        this.creationDate = creationDate;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLevelTitle() {
        return levelTitle;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public int getLevelId() {
        return levelId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static ExamDto convertToDto(Exam exam) {
        return new ExamDto(exam.getId(),
                exam.getName(),
                exam.getDescription(),
                exam.getLevel().getTitle(),
                exam.getLevel().getCode().name(),
                exam.getLevel().getId(),
                exam.getCreatedAt(),
                exam.getUpdatedAt());
    }
}
