package ir.iraniancyber.khaneshyar.model;

import jakarta.persistence.Entity;

@Entity
public class Exam extends BaseEntity {

    private String name;
    private int level;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
