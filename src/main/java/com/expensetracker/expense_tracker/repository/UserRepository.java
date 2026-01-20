package com.expensetracker.expense_tracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.expensetracker.expense_tracker.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
