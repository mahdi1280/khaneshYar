package ir.iraniancyber.khaneshyar.repository;

import ir.iraniancyber.khaneshyar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
