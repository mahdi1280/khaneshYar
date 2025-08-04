package ir.iraniancyber.khaneshyar.service;

import ir.iraniancyber.khaneshyar.model.ExamLevel;

import java.util.Optional;

public interface ExamLevelService {

    Optional<ExamLevel> findById(int id);

}
