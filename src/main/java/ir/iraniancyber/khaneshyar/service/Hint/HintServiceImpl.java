package ir.iraniancyber.khaneshyar.service.Hint;

import ir.iraniancyber.khaneshyar.model.Hint;
import ir.iraniancyber.khaneshyar.repository.HintRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class HintServiceImpl implements HintService {
    private HintRepository hintRepository;

    @Override
    public void save(Hint hint) {
        hintRepository.save(hint);
    }

    @Override
    public void delete(int id) {
        hintRepository.deleteById(id);
    }

    @Override
    public void update(Hint hint) {
        hint.setId(hint.getId());
        hintRepository.save(hint);
    }

}
