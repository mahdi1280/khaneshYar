package ir.iraniancyber.khaneshyar.dto.QuestionDto;

import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.Question;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class QuestionSaveDto {
    private final String title;
    private final int examId;

    public QuestionSaveDto(String title, int examId) {
        this.title = title;
        this.examId = examId;
    }

    @NotBlank(message = "question.save.dto.title.blank")
    public String getTitle() {
        return title;
    }



    @NotNull(message = "question.save.dto.exam.null")
    public int getExamId() {
        return examId;
    }

    public Question convertToQuestion(Exam exam) {
        Question question = new Question();
        question.setTitle(title);
        question.setExam(exam);
        return question;
    }
}
