package ir.iraniancyber.khaneshyar.dto;

import ir.iraniancyber.khaneshyar.model.Exam;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ExamDto implements Serializable {

    private final int id;
    private final String name;
    private final String description;
    private final String levelTitle;
    private final String levelCode;
    private final int levelId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ExamDto(int id, String name, String description, String levelTitle, String levelCode, int levelId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.levelTitle = levelTitle;
        this.levelCode = levelCode;
        this.levelId = levelId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Builder builder() {
        return new Builder();
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static ExamDto convertExam(Exam exam) {
        return ExamDto.builder()
                .setCreatedAt(exam.getCreatedAt())
                .setId(exam.getId())
                .setLevelCode(exam.getLevel().getCode().name())
                .setLevelId(exam.getLevel().getId())
                .setLevelTitle(exam.getLevel().getTitle())
                .setDescription(exam.getDescription())
                .setName(exam.getName())
                .setUpdatedAt(exam.getUpdatedAt())
                .build();
    }

    public static class Builder {

        private int id;
        private String name;
        private String description;
        private String levelTitle;
        private String levelCode;
        private int levelId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private Builder() {}

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setLevelTitle(String levelTitle) {
            this.levelTitle = levelTitle;
            return this;
        }

        public Builder setLevelCode(String levelCode) {
            this.levelCode = levelCode;
            return this;
        }

        public Builder setLevelId(int levelId) {
            this.levelId = levelId;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public ExamDto build() {
            return new ExamDto(id, name, description, levelTitle, levelCode, levelId, createdAt, updatedAt);
        }
    }
}
