package ir.iraniancyber.khaneshyar.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class SubmitExamDto {

        @NotNull(message = "submitExamDto.examId.notNull")
        private final int examId;

        @NotEmpty(message = "submitExamDto.answers.notEmpty")
        private final List<@Valid AnswerDto> answers;

        public SubmitExamDto(int examId, List<AnswerDto> answers) {
            this.examId = examId;
            this.answers = answers;
        }

        public int getExamId() {
            return examId;
        }

        public List<AnswerDto> getAnswers() {
            return answers;
        }

}
