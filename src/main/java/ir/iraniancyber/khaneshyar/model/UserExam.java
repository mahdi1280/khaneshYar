package ir.iraniancyber.khaneshyar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class UserExam extends BaseEntity {

    private User user;
    private Exam exam;

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
