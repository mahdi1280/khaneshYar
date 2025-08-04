package ir.iraniancyber.khaneshyar.service;

import ir.iraniancyber.khaneshyar.exeption.RuleException;
import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }


    @Override
    public void save(Exam exam) {
        examRepository.save(exam);
    }

    @Override
    public void update(Exam exam) {
        examRepository.save(exam);
    }

    @Override
    public void delete(int id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuleException("exam.not.found"));
        exam.setDisableDate(LocalDateTime.now());
        examRepository.save(exam);
    }

    @Override
    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public Optional<Exam> findById(int id) {
        return examRepository.findById(id);
    }
}
