package com.example.expensesplitter.controller;

import com.example.expensesplitter.model.Group;
import com.example.expensesplitter.repository.GroupRepository;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = "http://localhost:3000")
public class GroupController {

    private final GroupRepository repo;

    public GroupController(GroupRepository r) { this.repo = r; }

    @GetMapping
    public List<Group> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Group create(@RequestBody Group g) {
        g.setCreatedAt(LocalDateTime.now().toString());
        return repo.save(g);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}