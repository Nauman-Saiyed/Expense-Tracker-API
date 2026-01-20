package com.expensetracker.expense_tracker.service;

import com.expensetracker.expense_tracker.DTO.ExpenseRequest;
import com.expensetracker.expense_tracker.Exception.ResourceNotFoundException;
import com.expensetracker.expense_tracker.entity.Expense;
import com.expensetracker.expense_tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense save(Expense expense) {
        return (Expense) expenseRepository.save(expense);
    }

    public List<Expense> getByUser(String email) {
        return expenseRepository.findByUserEmail(email);
    }

    public Expense update(Long id, String email, ExpenseRequest request) {

        Expense expense = expenseRepository
                .findByIdAndUserEmail(id, email)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found or unauthorized"));

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setDate(request.getDate());

        return expenseRepository.save(expense);
    }


    public void delete(Long id, String email) {
        Expense expense = expenseRepository
                .findByIdAndUserEmail(id, email)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found or Unauthorized"));

        expenseRepository.delete(expense);
    }

    public Page<Expense> getByUser(String email, Pageable pageable) {
        return expenseRepository.findByUserEmail(email, pageable);
    }

    public List<Expense> filterExpenses(
            String email,
            String category,
            LocalDate startDate,
            LocalDate endDate,
            Double minAmount,
            Double maxAmount
    ) {
        return expenseRepository.filterExpenses(
                email, category, startDate, endDate, minAmount, maxAmount
        );
    }
}
