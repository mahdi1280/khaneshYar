package ir.iraniancyber.khaneshyar.service.level;

import ir.iraniancyber.khaneshyar.model.Level;
import ir.iraniancyber.khaneshyar.model.LevelExam;

import java.util.Optional;

public interface LevelService {

    Optional<Level> findByCode(LevelExam code);
}
