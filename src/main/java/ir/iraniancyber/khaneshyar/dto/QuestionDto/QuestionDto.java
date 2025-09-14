package ir.iraniancyber.khaneshyar.dto.QuestionDto;

import ir.iraniancyber.khaneshyar.dto.optionDto.OptionDto;
import ir.iraniancyber.khaneshyar.model.Question;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {
    private final int id;
    private final String title;
    private final String examName;
    private final int examId;
    private final String examLevel;
    private final LocalDateTime creationDate;
    private final LocalDateTime updatedAt;
    private final List<OptionDto> optionDtos;

    public QuestionDto(int id, String title, String examName, int examId, String examLevel, LocalDateTime creationDate, LocalDateTime updatedAt, List<OptionDto> optionDtos) {
        this.id = id;
        this.title = title;
        this.examName = examName;
        this.examId = examId;
        this.examLevel = examLevel;
        this.creationDate = creationDate;
        this.updatedAt = updatedAt;
        this.optionDtos = optionDtos;
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

    public List<OptionDto> getOptionDtos() {
        return optionDtos;
    }

    public static QuestionDto convertToDto(Question question, List<OptionDto> optionDtos) {
        return new QuestionDto(question.getId(),
                question.getTitle(),
                question.getExam().getName(),
                question.getExam().getId(),
                question.getExam().getLevel().getTitle(),
                question.getCreatedAt(),
                question.getUpdatedAt(),
                optionDtos);
    }
}
