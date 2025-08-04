package ir.iraniancyber.khaneshyar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class ExamLevel extends BaseEntity {

    private String title;
    private Code code;

    public String getTitle() {
        return title;
    }

    public ExamLevel setTitle(String title) {
        this.title = title;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public Code getCode() {
        return code;
    }

    public ExamLevel setCode(Code code) {
        this.code = code;
        return this;
    }

    public static enum Code {
        EASY, MEDIUM, HARD
    }
}
