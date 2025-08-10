package ir.iraniancyber.khaneshyar.service.exam;

import ir.iraniancyber.khaneshyar.customeExeption.RuleException;
import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.Level;
import ir.iraniancyber.khaneshyar.repository.ExamRepository;
import ir.iraniancyber.khaneshyar.service.level.LevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final LevelService levelService;

    public ExamServiceImpl(ExamRepository examRepository, LevelService levelService) {
        this.examRepository = examRepository;
        this.levelService = levelService;
    }

    @Override
    public void save(Exam exam) {
        examRepository.save(exam);
    }

    @Override
    public List<Exam> findAll() {
        return examRepository.findAllByDisableDateIsNull();
    }
}
