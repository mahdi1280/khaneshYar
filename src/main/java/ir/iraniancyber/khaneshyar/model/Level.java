package ir.iraniancyber.khaneshyar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class Level extends BaseEntity {

    private LevelExam code;
    private String title;

    @Enumerated(EnumType.STRING)
    public LevelExam getCode() {
        return code;
    }

    public Level setCode(LevelExam code) {
        this.code = code;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Level setTitle(String title) {
        this.title = title;
        return this;
    }
}
