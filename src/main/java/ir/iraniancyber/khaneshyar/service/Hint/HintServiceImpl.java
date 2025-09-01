package ir.iraniancyber.khaneshyar.service.Hint;

import ir.iraniancyber.khaneshyar.customeExeption.RuleException;
import ir.iraniancyber.khaneshyar.model.Hint;
import ir.iraniancyber.khaneshyar.repository.HintRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HintServiceImpl implements HintService {
    private final HintRepository hintRepository;

    public HintServiceImpl(HintRepository hintRepository) {
        this.hintRepository = hintRepository;
    }

    @Override
    public void save(Hint hint) {
        hintRepository.save(hint);
    }

    @Override
    public void delete(int id) {
        Hint hint = hintRepository.findById(id)
                .orElseThrow(() -> new RuleException("hint.not.found"));
        hint.setDisableDate(LocalDateTime.now());
        hintRepository.save(hint);
    }

    @Override
    public void update(int id, Hint hint) {
        Hint oldHint = hintRepository.findById(id)
                .orElseThrow(() -> new RuleException("hint.not.found"));

        oldHint.setLevel(hint.getLevel());
        oldHint.setTitle(hint.getTitle());

        hintRepository.save(hint);
    }

    @Override
    public List<Hint> findByQuestionId(int questionId) {
        return hintRepository.findByQuestionIdAndDisableDateIsNull(questionId);
    }


}
