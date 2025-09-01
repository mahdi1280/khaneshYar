package ir.iraniancyber.khaneshyar.service.Hint;

import ir.iraniancyber.khaneshyar.model.Hint;

import java.util.List;

public interface HintService {
    void save(Hint hint);

    void delete(int id);

    void update(int id, Hint hint);
    List<Hint> findByQuestionId(int questionId);
}
