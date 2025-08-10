package ir.iraniancyber.khaneshyar.dto;

import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.Level;
import ir.iraniancyber.khaneshyar.model.LevelExam;

public class ExamSaveDto {

    private final String name;
    private final LevelExam level;
    private final String description;

    public ExamSaveDto(String name, LevelExam level, String description) {
        this.name = name;
        this.level = level;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public LevelExam getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public Exam convertToExam(Level level) {
        Exam exam = new Exam();
        exam.setDescription(description);
        exam.setName(name);
        exam.setLevel(level);
        return exam;
    }
}
