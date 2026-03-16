package com.example.expensesplitter.service;

import com.example.expensesplitter.model.*;
import com.example.expensesplitter.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepo;
    private final GroupRepository groupRepo;
    private final SettlementService settlementService;

    public ExpenseService(ExpenseRepository er, GroupRepository gr, SettlementService ss) {
        this.expenseRepo = er;
        this.groupRepo = gr;
        this.settlementService = ss;
    }

    public Group createGroup(Group g) {
        g.setCreatedAt(LocalDateTime.now().toString());
        return groupRepo.save(g);
    }

    public Expense addExpense(Expense e) {
        e.setCreatedAt(LocalDateTime.now().toString());
        return expenseRepo.save(e);
    }

    public List<Expense> getExpenses(Long groupId) {
        return expenseRepo.findByGroupId(groupId);
    }

    public List<Map<String, Object>> getSettlements(Long groupId) {
        List<Expense> expenses = expenseRepo.findByGroupId(groupId);
        return settlementService.calculateSettlements(expenses);
    }

    public Map<String, Double> getBalances(Long groupId) {
        List<Expense> expenses = expenseRepo.findByGroupId(groupId);
        Map<String, Double> balance = new HashMap<>();
        for (Expense e : expenses) {
            String[] members = e.getSplitAmong().split(",");
            double share = e.getAmount() / members.length;
            balance.merge(e.getPaidBy(), e.getAmount(), Double::sum);
            for (String m : members) balance.merge(m.trim(), -share, Double::sum);
        }
        return balance;
    }
}