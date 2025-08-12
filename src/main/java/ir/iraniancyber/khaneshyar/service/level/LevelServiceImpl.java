package ir.iraniancyber.khaneshyar.service.level;

import ir.iraniancyber.khaneshyar.model.Level;
import ir.iraniancyber.khaneshyar.model.LevelExam;
import ir.iraniancyber.khaneshyar.repository.LevelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public Optional<Level> findByCode(LevelExam code) {

        return levelRepository.findByCode(code);
    }
}
