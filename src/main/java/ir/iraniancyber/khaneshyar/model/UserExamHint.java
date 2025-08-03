package ir.iraniancyber.khaneshyar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class UserExamHint extends BaseEntity {

    private UserExam userExam;
    private Hint hint;
    private Question question;

    @ManyToOne
    public UserExam getUserExam() {
        return userExam;
    }

    public void setUserExam(UserExam userExam) {
        this.userExam = userExam;
    }

    @ManyToOne
    public Hint getHint() {
        return hint;
    }

    public void setHint(Hint hint) {
        this.hint = hint;
    }

    @ManyToOne
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
