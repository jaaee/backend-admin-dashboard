package com.example.admin_dashboard.repository;

import com.example.admin_dashboard.enums.RiskLevel;
import com.example.admin_dashboard.enums.TransactionStatus;
import com.example.admin_dashboard.model.Transaction;
import com.example.admin_dashboard.projection.ChannelBreakdownProjection;
import com.example.admin_dashboard.projection.RiskAnalysisProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> ,
        JpaSpecificationExecutor<Transaction> {

    List<Transaction> findTop10ByOrderByCreatedAtDesc();

    @Query("""
        SELECT
            c.channelName as channelName,
            SUM(t.amount) as totalAmount
        FROM Transaction t
        JOIN t.channel c
        GROUP BY c.channelName
    """)
    List<ChannelBreakdownProjection> getChannelBreakdown();

    long countByStatus(TransactionStatus status);

    long countByStatusIn(List<TransactionStatus> statuses);

    long countByRiskLevelIn(List<RiskLevel> riskLevels);

@Query("""
    SELECT
        t.riskLevel as riskLevel,
        COUNT(t) as count
    FROM Transaction t
    GROUP BY t.riskLevel
""")
List<RiskAnalysisProjection> getRiskAnalysis();

    @Query("""
       SELECT COUNT(t)
       FROM Transaction t
       WHERE t.createdAt >= :fromTime
       """)
    Long countTransactionsAfter(LocalDateTime fromTime);
}

