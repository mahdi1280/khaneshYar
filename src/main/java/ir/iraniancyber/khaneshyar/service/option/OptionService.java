package ir.iraniancyber.khaneshyar.service.option;


import java.util.List;
import ir.iraniancyber.khaneshyar.model.Option;
import ir.iraniancyber.khaneshyar.model.Question;

public interface OptionService {
    void Save(Option option);
    List<Option> findByQuestionId(int questionId);
    void delete(int id);
    void update(int id,Option option);

}
