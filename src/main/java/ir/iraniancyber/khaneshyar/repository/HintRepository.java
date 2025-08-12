package ir.iraniancyber.khaneshyar.repository;

import ir.iraniancyber.khaneshyar.model.Hint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HintRepository extends JpaRepository<Hint, Integer> {
}
