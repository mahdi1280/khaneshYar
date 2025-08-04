package ir.iraniancyber.khaneshyar.service;

import ir.iraniancyber.khaneshyar.model.ExamLevel;
import ir.iraniancyber.khaneshyar.repository.ExamLevelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamLevelServiceImpl implements ExamLevelService {

    private final ExamLevelRepository examLevelRepository;

    public ExamLevelServiceImpl(ExamLevelRepository examLevelRepository) {
        this.examLevelRepository = examLevelRepository;
    }

    @Override
    public Optional<ExamLevel> findById(int id) {
        return examLevelRepository.findById(id);
    }
}
