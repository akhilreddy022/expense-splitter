package com.example.expensesplitter.service;

import com.example.expensesplitter.model.Expense;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SettlementService {

    public List<Map<String, Object>> calculateSettlements(List<Expense> expenses) {
        Map<String, Double> balance = new HashMap<>();

        for (Expense e : expenses) {
            String[] members = e.getSplitAmong().split(",");
            double share = e.getAmount() / members.length;
            balance.merge(e.getPaidBy(), e.getAmount(), Double::sum);
            for (String m : members) {
                balance.merge(m.trim(), -share, Double::sum);
            }
        }

        List<Map.Entry<String, Double>> creditors = new ArrayList<>();
        List<Map.Entry<String, Double>> debtors = new ArrayList<>();

        for (Map.Entry<String, Double> entry : balance.entrySet()) {
            if (entry.getValue() > 0.01) creditors.add(entry);
            else if (entry.getValue() < -0.01) debtors.add(entry);
        }

        creditors.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));
        debtors.sort((a, b) -> Double.compare(a.getValue(), b.getValue()));

        List<Map<String, Object>> settlements = new ArrayList<>();
        int i = 0, j = 0;

        while (i < creditors.size() && j < debtors.size()) {
            double credit = creditors.get(i).getValue();
            double debt = Math.abs(debtors.get(j).getValue());
            double amount = Math.min(credit, debt);

            Map<String, Object> txn = new HashMap<>();
            txn.put("from", debtors.get(j).getKey());
            txn.put("to", creditors.get(i).getKey());
            txn.put("amount", Math.round(amount * 100.0) / 100.0);
            settlements.add(txn);

            creditors.get(i).setValue(credit - amount);
            debtors.get(j).setValue(-(debt - amount));

            if (creditors.get(i).getValue() < 0.01) i++;
            if (Math.abs(debtors.get(j).getValue()) < 0.01) j++;
        }

        return settlements;
    }
}