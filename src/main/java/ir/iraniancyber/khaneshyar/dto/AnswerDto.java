package ir.iraniancyber.khaneshyar.dto;

public class AnswerDto {

    private final int questionId;
    private final int optionId;

    public int getQuestionId() {
        return questionId;
    }

    public int getOptionId() {
        return optionId;
    }

    public AnswerDto(int questionId, int optionId) {
        this.questionId = questionId;
        this.optionId = optionId;
    }
}
