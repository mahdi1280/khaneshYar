package ir.iraniancyber.khaneshyar.service.exam;

import ir.iraniancyber.khaneshyar.customeExeption.RuleException;
import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.repository.ExamRepository;
import ir.iraniancyber.khaneshyar.service.level.LevelService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public Exam findById(int id) {
      return examRepository.findById(id)
              .orElseThrow(()->new RuleException("exam.not.found"));
    }

    @Override
    public void delete(int id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuleException("exam.not.found"));
        exam.setDisableDate(LocalDateTime.now());
        examRepository.save(exam);
    }

    @Override
    public void update(int id ,Exam updateExam) {
        Exam exam= examRepository.findById(id)
                 .orElseThrow(()-> new RuleException("exam.not.found"));
         exam.setName(updateExam.getName());
         exam.setDescription(updateExam.getDescription());
         exam.setLevel(updateExam.getLevel());
        exam.setUpdatedAt(LocalDateTime.now());

         examRepository.save(exam);

    }

}
