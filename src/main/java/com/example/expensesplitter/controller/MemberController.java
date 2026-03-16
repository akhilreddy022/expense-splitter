package com.example.expensesplitter.controller;

import com.example.expensesplitter.model.Member;
import com.example.expensesplitter.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {

    private final MemberRepository repo;

    public MemberController(MemberRepository r) { this.repo = r; }

    @GetMapping("/group/{groupId}")
    public List<Member> getMembers(@PathVariable Long groupId) {
        return repo.findByGroupId(groupId);
    }

    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return repo.save(member);
    }

    @Transactional
    @DeleteMapping("/group/{groupId}/name/{name}")
    public void deleteMember(@PathVariable Long groupId, @PathVariable String name) {
        repo.deleteByNameAndGroupId(name, groupId);
    }
}