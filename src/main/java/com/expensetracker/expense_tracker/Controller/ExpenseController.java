package com.expensetracker.expense_tracker.Controller;


import com.expensetracker.expense_tracker.DTO.ExpenseRequest;
import com.expensetracker.expense_tracker.entity.Expense;
import com.expensetracker.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody ExpenseRequest request, Authentication authentication) {

        Expense expense = new Expense();

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setDate(request.getDate());
        expense.setUserEmail(authentication.getName());

        Expense savedExpense = expenseService.save(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExpense);
    }

//    @GetMapping
//    public ResponseEntity<List<Expense>> getMyExpenses(Authentication authentication) {
//
//        String email = authentication.getName();
//
//        return ResponseEntity.ok(expenseService.getByUser(email));
//
//    }


    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpenses(@PathVariable Long id, @Valid @RequestBody ExpenseRequest request, Authentication authentication) {

        String email = authentication.getName();
        Expense updatedExpense = expenseService.update(id, email, request);
        return ResponseEntity.ok(updatedExpense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpenses(@PathVariable Long id, Authentication authentication) {

        String email = authentication.getName();

        expenseService.delete(id, email);

        return ResponseEntity.ok(
                Map.of(
                        "message", "Expense Deleted Successfully"
                )
        );
    }

    @GetMapping
    public ResponseEntity<Page<Expense>> getMyExpenses(
            Authentication authentication,
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                expenseService.getByUser(authentication.getName(), pageable)
        );
    }


    @GetMapping("/filter")
    public ResponseEntity<List<Expense>> filterExpense(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                expenseService.filterExpenses(
                        authentication.getName(), category, startDate, endDate, minAmount, maxAmount
                )
        );
    }
}
