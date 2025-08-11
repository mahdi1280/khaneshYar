package ir.iraniancyber.khaneshyar.dto;

import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.Level;
import ir.iraniancyber.khaneshyar.model.Question;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class QuestionSaveDto {
    private final String title;
    private final int exam_id;

    public QuestionSaveDto(String title, int examId) {
        this.title = title;
       this. exam_id = examId;
    }


    @NotBlank(message = "question.save.dto.title.blank")
    public String getTitle() {
        return title;
    }
    @NotNull(message = "question.save.dto.exam.null")
    public int getExam_id() {
        return exam_id;
    }
    public Question convertToQuestion(Exam exam) {
        Question question = new Question();
        question.setTitle(title);
        question.setExam(exam);
        return question;
    }
}
