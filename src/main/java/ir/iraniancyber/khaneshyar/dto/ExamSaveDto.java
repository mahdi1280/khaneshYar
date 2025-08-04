package ir.iraniancyber.khaneshyar.dto;

import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.ExamLevel;

import java.io.Serializable;

public class ExamSaveDto implements Serializable {

    private final String name;
    private final String description;
    private final Integer level;

    public ExamSaveDto(String name, String description, Integer level) {
        this.name = name;
        this.description = description;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getLevel() {
        return level;
    }

    public Exam convertToExam(ExamLevel examLevel) {
        return Exam.builder()
                .setDescription(description)
                .setName(name)
                .setLevel(examLevel)
                .build();

    }
}
