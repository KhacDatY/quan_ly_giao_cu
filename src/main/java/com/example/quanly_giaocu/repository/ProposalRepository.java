package com.example.quanly_giaocu.repository;

import com.example.quanly_giaocu.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long>, JpaSpecificationExecutor<Proposal> {
    List<Proposal> findByCreatorId(UUID creatorId);
    @Query("SELECT SUM(COALESCE(p.actualTotal, p.estimatedTotal)) FROM Proposal p " +
            "WHERE p.creator.group.id = :groupId " +
            "AND YEAR(p.requiredDate) = :year AND QUARTER(p.requiredDate) = :quarter " +
            "AND p.status IN ('COMPLETED', 'PURCHASING')")
    Double sumSpentAmount(@Param("groupId") UUID groupId, @Param("year") int year, @Param("quarter") int quarter);

    // Tính tổng "Đã cam kết" (đang chờ chi / đã duyệt)
    @Query("SELECT SUM(p.estimatedTotal) FROM Proposal p " +
            "WHERE p.creator.group.id = :groupId " +
            "AND YEAR(p.requiredDate) = :year AND QUARTER(p.requiredDate) = :quarter " +
            "AND p.status IN ('PENDING_DIRECTOR', 'APPROVED')")
    Double sumCommittedAmount(@Param("groupId") UUID groupId, @Param("year") int year, @Param("quarter") int quarter);
}