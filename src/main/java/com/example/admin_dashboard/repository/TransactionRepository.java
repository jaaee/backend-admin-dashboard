package com.example.admin_dashboard.repository;

import com.example.admin_dashboard.enums.RiskLevel;
import com.example.admin_dashboard.enums.TransactionStatus;
import com.example.admin_dashboard.model.Transaction;
import com.example.admin_dashboard.projection.ChannelBreakdownProjection;
import com.example.admin_dashboard.projection.RiskAnalysisProjection;
import com.example.admin_dashboard.projection.TransactionMetricsProjection;
import com.example.admin_dashboard.projection.TransactionTrendProjection;
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


    @Query(value = """
        WITH latest_date AS (
            SELECT MAX(created_at)::date AS max_date
            FROM transactions
        ),
        stats AS (
            SELECT
                COUNT(*) FILTER (
                    WHERE created_at >= max_date
                      AND created_at < max_date + INTERVAL '1 day'
                ) AS today_total,

                COUNT(*) FILTER (
                    WHERE created_at >= max_date - INTERVAL '1 day'
                      AND created_at < max_date
                ) AS yesterday_total,

                COUNT(*) FILTER (
                    WHERE status = 'SUCCESS'
                      AND created_at >= max_date
                      AND created_at < max_date + INTERVAL '1 day'
                ) AS today_success,

                COUNT(*) FILTER (
                    WHERE status = 'SUCCESS'
                      AND created_at >= max_date - INTERVAL '1 day'
                      AND created_at < max_date
                ) AS yesterday_success,

                COUNT(*) FILTER (
                    WHERE status = 'FAILED'
                      AND created_at >= max_date
                      AND created_at < max_date + INTERVAL '1 day'
                ) AS today_failed,

                COUNT(*) FILTER (
                    WHERE status = 'FAILED'
                      AND created_at >= max_date - INTERVAL '1 day'
                      AND created_at < max_date
                ) AS yesterday_failed,

                COUNT(*) FILTER (
                    WHERE status IN ('PENDING', 'PROCESSING')
                      AND created_at >= max_date
                      AND created_at < max_date + INTERVAL '1 day'
                ) AS today_pending,

                COUNT(*) FILTER (
                    WHERE status IN ('PENDING', 'PROCESSING')
                      AND created_at >= max_date - INTERVAL '1 day'
                      AND created_at < max_date
                ) AS yesterday_pending,

                COUNT(*) FILTER (
                    WHERE risk_level IN ('HIGH', 'CRITICAL')
                      AND created_at >= max_date
                      AND created_at < max_date + INTERVAL '1 day'
                ) AS today_high_risk,

                COUNT(*) FILTER (
                    WHERE risk_level IN ('HIGH', 'CRITICAL')
                      AND created_at >= max_date - INTERVAL '1 day'
                      AND created_at < max_date
                ) AS yesterday_high_risk

            FROM transactions
            CROSS JOIN latest_date
        )
        SELECT
            ROUND(((today_total - yesterday_total)::numeric / NULLIF(yesterday_total, 0)) * 100, 2) AS totalChangePct,

            ROUND(((today_success - yesterday_success)::numeric / NULLIF(yesterday_success, 0)) * 100, 2) AS successChangePct,

            ROUND(((today_failed - yesterday_failed)::numeric / NULLIF(yesterday_failed, 0)) * 100, 2) AS failedChangePct,

            ROUND(((today_pending - yesterday_pending)::numeric / NULLIF(yesterday_pending, 0)) * 100, 2) AS pendingChangePct,

            ROUND(((today_high_risk - yesterday_high_risk)::numeric / NULLIF(yesterday_high_risk, 0)) * 100, 2) AS highRiskChangePct
        FROM stats
        """, nativeQuery = true)
    TransactionMetricsProjection getChangeInTransactions();


    @Query(value = """

            WITH max_date AS (
    SELECT MAX(created_at::date) AS max_dt
    FROM transactions
),
last_7_days AS (
    SELECT generate_series(
        (SELECT max_dt - INTERVAL '6 days' FROM max_date),
        (SELECT max_dt FROM max_date),
        INTERVAL '1 day'
    )::date AS txn_date
)
SELECT


    COUNT(t.id) AS totalTransactions,

    COUNT(*) FILTER (WHERE t.status IN ('PENDING','PROCESSING')) AS totalPending,
    COUNT(*) FILTER (WHERE t.status = 'SUCCESS') AS totalSuccess,
    COUNT(*) FILTER (WHERE t.status = 'FAILED') AS totalFailed,
    COUNT(*) FILTER (WHERE t.risk_level IN ('HIGH','CRITICAL')) AS totalHighRisk

FROM last_7_days d
LEFT JOIN transactions t
    ON t.created_at::date = d.txn_date
GROUP BY d.txn_date
ORDER BY d.txn_date;
""", nativeQuery = true)
    List<TransactionTrendProjection> getTransactionTrends();
}

