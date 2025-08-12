package ir.iraniancyber.khaneshyar.service.option;


import ir.iraniancyber.khaneshyar.model.Option;

import java.util.List;

public interface OptionService {
    void Save(Option option);

    List<Option> findByQuestionId(int questionId);

    void delete(int id);

    void update(int id, Option option);

}
