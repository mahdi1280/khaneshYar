package ir.iraniancyber.khaneshyar.dto.ExamDto;

import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.Level;
import ir.iraniancyber.khaneshyar.model.LevelExam;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ExamSaveDto {

    private final String name;
    private final LevelExam level;
    private final String description;

    public ExamSaveDto(String name, LevelExam level, String description) {
        this.name = name;
        this.level = level;
        this.description = description;
    }

    @NotBlank(message = "exam.save.dto.name.blank")
    public String getName() {
        return name;
    }

    @NotNull(message = "exam.save.dto.level.null")
    public LevelExam getLevel() {
        return level;
    }

    @NotBlank(message = "exam.save.dto.description.null")
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
