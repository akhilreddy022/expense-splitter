package com.example.expensesplitter.controller;

import com.example.expensesplitter.model.Expense;
import com.example.expensesplitter.repository.ExpenseRepository;
import com.example.expensesplitter.service.ExpenseService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "http://localhost:3000")
public class ExpenseController {

    private final ExpenseService service;
    private final ExpenseRepository expenseRepo;

    public ExpenseController(ExpenseService s, ExpenseRepository er) {
        this.service = s;
        this.expenseRepo = er;
    }

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return service.addExpense(expense);
    }

    @GetMapping("/group/{groupId}")
    public List<Expense> getExpenses(@PathVariable Long groupId) {
        return service.getExpenses(groupId);
    }

    @GetMapping("/group/{groupId}/settlements")
    public List<Map<String, Object>> getSettlements(@PathVariable Long groupId) {
        return service.getSettlements(groupId);
    }

    @GetMapping("/group/{groupId}/balances")
    public Map<String, Double> getBalances(@PathVariable Long groupId) {
        return service.getBalances(groupId);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseRepo.deleteById(id);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense updated) {
        Expense e = expenseRepo.findById(id).orElseThrow();
        e.setDescription(updated.getDescription());
        e.setAmount(updated.getAmount());
        e.setCategory(updated.getCategory());
        e.setPaidBy(updated.getPaidBy());
        e.setSplitAmong(updated.getSplitAmong());
        e.setNotes(updated.getNotes());
        return expenseRepo.save(e);
    }
}
