package ir.iraniancyber.khaneshyar.service.Hint;

import ir.iraniancyber.khaneshyar.model.Hint;
import ir.iraniancyber.khaneshyar.repository.HintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
        Hint hint = hintRepository.findById(id).orElseThrow();
        hint.setDisableDate(LocalDateTime.now());
        hintRepository.save(hint);
    }

    @Override
    public void update(Hint hint) {
        hint.setId(hint.getId());
        hintRepository.save(hint);
    }

}
