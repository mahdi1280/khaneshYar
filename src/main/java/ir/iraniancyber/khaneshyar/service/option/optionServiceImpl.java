package ir.iraniancyber.khaneshyar.service.option;

import ir.iraniancyber.khaneshyar.customeExeption.RuleException;
import ir.iraniancyber.khaneshyar.model.Question;
import ir.iraniancyber.khaneshyar.repository.OptionRepository;

import ir.iraniancyber.khaneshyar.model.Option;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class optionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;

    public optionServiceImpl(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @Override
    public void Save(Option option) {
        optionRepository.save(option);
    }

    @Override
    public List<Option> findByQuestionId(int questionId) {
        return optionRepository.findByQuestionIdAndDisableDateIsNull(questionId);
    }

    @Override
    public void delete(int id) {
       Option option=optionRepository.findById(id)
               .orElseThrow(()-> new RuleException("option.not.found"));
       option.setDisableDate(LocalDateTime.now());
       optionRepository.save(option);
    }

    @Override
    public void update(int id, Option updatedOption) {
        Option oldOption=optionRepository.findById(id)
                .orElseThrow(()-> new RuleException("option.not.found"));
        oldOption.setTitle(updatedOption.getTitle());
        oldOption.setCorrect(updatedOption.isCorrect());
        oldOption.setQuestion(updatedOption.getQuestion());
        optionRepository.save(oldOption);
    }
}
