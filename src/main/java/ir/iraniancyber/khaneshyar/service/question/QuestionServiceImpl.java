package ir.iraniancyber.khaneshyar.service.question;

import ir.iraniancyber.khaneshyar.customeExeption.RuleException;
import ir.iraniancyber.khaneshyar.model.Question;
import ir.iraniancyber.khaneshyar.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void SaveQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void update(int id, Question updatedQues) {
        Question question=questionRepository.findById(id)
                .orElseThrow(()-> new RuleException("Question.not.found"));
        question.setTitle(updatedQues.getTitle());
        question.setExam(updatedQues.getExam());
        question.setUpdatedAt(LocalDateTime.now());
        questionRepository.save(question);
    }

    @Override
    public void delete(int id) {
      Question question=questionRepository.findById(id)
              .orElseThrow(()-> new RuleException("Question.not.found"));
        question.setDisableDate(LocalDateTime.now());
      questionRepository.save(question);
    }

    @Override
    public List<Question> findAll()  {
        return questionRepository.findAllByDisableDateIsNull();
    }

    @Override
    public Question findById(int id) {
        Question question=questionRepository.findById(id)
                .orElseThrow( ()->new RuleException("Question.not.found"));
        return question;
    }
}
