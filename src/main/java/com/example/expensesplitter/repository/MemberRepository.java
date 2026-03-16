package com.example.expensesplitter.repository;

import com.example.expensesplitter.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByGroupId(Long groupId);
    void deleteByNameAndGroupId(String name, Long groupId);
}
