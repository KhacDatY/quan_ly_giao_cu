package com.example.quanly_giaocu.repository;

import com.example.quanly_giaocu.entity.GroupBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupBudgetRepository extends JpaRepository<GroupBudget, Long> {
    Optional<GroupBudget> findByGroupIdAndYearAndQuarter(UUID groupId, int year, int quarter);
}