package com.example.quanly_giaocu.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "group_budgets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupBudget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int quarter;

    @Column(nullable = false)
    private Double allocatedAmount; // Ngân sách được cấp đầu quý
}