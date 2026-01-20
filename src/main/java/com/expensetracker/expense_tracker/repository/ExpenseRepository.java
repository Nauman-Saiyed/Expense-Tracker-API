package com.expensetracker.expense_tracker.repository;

import com.expensetracker.expense_tracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Optional<Expense> findByIdAndUserEmail(Long id, String userEmail);

    List<Expense> findByUserEmail(String userEmail);

    Page<Expense> findByUserEmail(String userEmail, Pageable pageable);

    @Query("""
            SELECT e FROM Expense e
            WHERE e.userEmail = :email
            AND (:category IS NULL OR e.category = :category)
            AND (:startDate IS NULL OR e.date >= :startDate)
            AND (:endDate IS NULL OR e.date <= :endDate)
            AND (:minAmount IS NULL or e.amount >= :minAmount)
            AND (:maxAmount IS NULL or e.amount <= :maxAmount)
            """)
    List<Expense> filterExpenses(
            @Param("email") String email,
            @Param("category") String category,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("minAmount") Double minAmount,
            @Param("maxAmount") Double maxAmount
    );
}
