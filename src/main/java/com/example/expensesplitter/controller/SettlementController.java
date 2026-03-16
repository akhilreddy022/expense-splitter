package com.example.expensesplitter.controller;

import com.example.expensesplitter.model.Settlement;
import com.example.expensesplitter.repository.SettlementRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/settlements")
@CrossOrigin(origins = "http://localhost:3000")
public class SettlementController {

    private final SettlementRepository repo;

    public SettlementController(SettlementRepository r) { this.repo = r; }

    @GetMapping("/group/{groupId}")
    public List<Settlement> getByGroup(@PathVariable Long groupId) {
        return repo.findByGroupId(groupId);
    }

    @PostMapping
    public Settlement save(@RequestBody Settlement s) {
        return repo.save(s);
    }

    @PutMapping("/{id}/paid")
    public Settlement markPaid(@PathVariable Long id) {
        Settlement s = repo.findById(id).orElseThrow();
        s.setPaid(true);
        s.setPaidAt(LocalDateTime.now().toString());
        return repo.save(s);
    }

    @PutMapping("/{id}/unpaid")
    public Settlement markUnpaid(@PathVariable Long id) {
        Settlement s = repo.findById(id).orElseThrow();
        s.setPaid(false);
        s.setPaidAt(null);
        return repo.save(s);
    }

    @Transactional
    @DeleteMapping("/group/{groupId}")
    public void deleteByGroup(@PathVariable Long groupId) {
        repo.deleteByGroupId(groupId);
    }
}