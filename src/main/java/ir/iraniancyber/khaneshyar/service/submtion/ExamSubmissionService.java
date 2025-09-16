package ir.iraniancyber.khaneshyar.service.submtion;

import ir.iraniancyber.khaneshyar.dto.SubmitExamDto;

public interface ExamSubmissionService {

    void submitExam(SubmitExamDto dto);

    SubmitExamDto getUserExamAnswers(int userId, int examId);
}
