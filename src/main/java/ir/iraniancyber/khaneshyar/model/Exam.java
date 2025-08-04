package ir.iraniancyber.khaneshyar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Exam extends BaseEntity {

    private String name;
    private ExamLevel level;
    private String description;

    public Exam(String name, ExamLevel level, String description) {
        this.name = name;
        this.level = level;
        this.description = description;
    }

    public Exam() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public ExamLevel getLevel() {
        return level;
    }

    public void setLevel(ExamLevel level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Builder{

        private String name;
        private String description;
        private ExamLevel level;

        private Builder() {}

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setLevel(ExamLevel level) {
            this.level = level;
            return this;
        }

        public Exam build() {
            return new Exam(name, level, description);
        }
    }
}
