package ir.iraniancyber.khaneshyar.dto;

import java.time.LocalDateTime;

public class ExamUserResponseDto {

    private int examId;
    private String examName;
    private LocalDateTime creationDate;
    private float score;
    private int userExamId;

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getUserExamId() {
        return userExamId;
    }

    public void setUserExamId(int userExamId) {
        this.userExamId = userExamId;
    }
}
