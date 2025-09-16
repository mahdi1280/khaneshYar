package ir.iraniancyber.khaneshyar.dto;

import java.time.LocalDateTime;

public class ExamUserResponseDto {

    private String examName;
    private LocalDateTime creationDate;

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
