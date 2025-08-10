package ir.iraniancyber.khaneshyar.repository;

import ir.iraniancyber.khaneshyar.model.Level;
import ir.iraniancyber.khaneshyar.model.LevelExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LevelRepository extends JpaRepository<Level, Integer> {

    Optional<Level> findByCode(LevelExam code);
}
