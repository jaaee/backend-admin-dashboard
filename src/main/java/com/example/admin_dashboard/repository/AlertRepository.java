package com.example.admin_dashboard.repository;

import com.example.admin_dashboard.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findAllByOrderByCreatedDateDesc();

    @Query("""
       SELECT COUNT(a)
       FROM Alert a
       WHERE a.type = 'FRAUD'
       """)
    Long countFraudAlerts();

}
