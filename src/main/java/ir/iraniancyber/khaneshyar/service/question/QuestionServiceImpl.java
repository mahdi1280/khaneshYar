package ir.iraniancyber.khaneshyar.service.question;

import ir.iraniancyber.khaneshyar.customeExeption.RuleException;
import ir.iraniancyber.khaneshyar.dto.CompleteSaveDto;
import ir.iraniancyber.khaneshyar.model.Exam;
import ir.iraniancyber.khaneshyar.model.Hint;
import ir.iraniancyber.khaneshyar.model.Option;
import ir.iraniancyber.khaneshyar.model.Question;
import ir.iraniancyber.khaneshyar.repository.ExamRepository;
import ir.iraniancyber.khaneshyar.repository.HintRepository;
import ir.iraniancyber.khaneshyar.repository.OptionRepository;
import ir.iraniancyber.khaneshyar.repository.QuestionRepository;
import ir.iraniancyber.khaneshyar.service.Hint.HintService;
import ir.iraniancyber.khaneshyar.service.option.OptionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final OptionService optionService;
    private final HintService hintService;
    private final ExamRepository examRepository;
    private final OptionRepository optionRepository;
    private final HintRepository hintRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, OptionService optionService, HintService hintService, ExamRepository examRepository, OptionRepository optionRepository, HintRepository hintRepository) {
        this.questionRepository = questionRepository;
        this.optionService = optionService;
        this.hintService = hintService;
        this.examRepository = examRepository;
        this.optionRepository = optionRepository;
        this.hintRepository = hintRepository;
    }

    @Override
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void updateQuestion(int id, Question updatedQues) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuleException("Question.not.found"));
        question.setTitle(updatedQues.getTitle());
        question.setExam(updatedQues.getExam());
        question.setUpdatedAt(LocalDateTime.now());
        questionRepository.save(question);
    }

    @Override
    public void delete(int id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuleException("Question.not.found"));
        question.setDisableDate(LocalDateTime.now());
        questionRepository.save(question);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAllByDisableDateIsNull();
    }

    @Override
    public Question findById(int id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuleException("Question.not.found"));
    }

    @Override
    public int saveCompleted(CompleteSaveDto completeSaveDto) {

        Exam exam = examRepository.findById(completeSaveDto.getExamId())
                .orElseThrow(() -> new RuleException("Exam.not.found"));

        //question
        Question question = new Question();
        question.setTitle(completeSaveDto.getTitle());
        question.setExam(exam);
        saveQuestion(question);

        //option
        List<Option> options = completeSaveDto.getOptionSaveDtos()
                .stream().map(o -> {
                    Option option = new Option();
                    option.setQuestion(question);
                    option.setCorrect(o.isCorrect());
                    option.setTitle(o.getTitle());
                    return option;
                }).collect(Collectors.toList());

        optionRepository.saveAll(options);

        //hint

        List<Hint> hints = completeSaveDto.getHintSaveDtos()
                .stream().map(h -> {
                    Hint hint = new Hint();
                    hint.setQuestion(question);
                    hint.setLevel(h.getLevel());
                    hint.setTitle(h.getTitle());
                    return hint;
                }).collect(Collectors.toList());
        hintRepository.saveAll(hints);

        return question.getId();
    }

    @Override
    public int updateCompleted(CompleteSaveDto completeSaveDto) {

        int questionId = completeSaveDto.getOptionSaveDtos().get(0).getQuestionId();
        //Question Update
        Question oldQuestion = findById(questionId);
        oldQuestion.setTitle(completeSaveDto.getTitle());
        saveQuestion(oldQuestion);
        System.out.println(oldQuestion.getExam().getId());

        //Options Update
        List<Option> options = optionService.findByQuestionId(questionId);

        for (int i = 0; i < options.size(); i++) {
            options.get(i).setTitle(completeSaveDto.getOptionSaveDtos().get(i).getTitle());
            options.get(i).setCorrect(completeSaveDto.getOptionSaveDtos().get(i).isCorrect());

        }
        optionRepository.saveAll(options);

        //Hints Update
        List<Hint> hints = hintService.findByQuestionId(questionId);
        for (int i = 0; i < hints.size(); i++) {
            hints.get(i).setTitle(completeSaveDto.getHintSaveDtos().get(i).getTitle());
            hints.get(i).setLevel(completeSaveDto.getHintSaveDtos().get(i).getLevel());

        }

        hintRepository.saveAll(hints);

        return questionId;
    }


    @Override
    public List<Question> findAllByExam(Exam exam) {
        return questionRepository.findAllByExamAndDisableDateIsNull(exam);
    }
}
