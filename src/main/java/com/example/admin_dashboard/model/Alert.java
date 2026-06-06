package com.example.admin_dashboard.model;

import com.example.admin_dashboard.enums.AlertSeverity;
import com.example.admin_dashboard.enums.AlertSource;
import com.example.admin_dashboard.enums.AlertType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alertCode;

    private String title;

    private String message;

    @Enumerated(EnumType.STRING)
    private AlertType type;

    @Enumerated(EnumType.STRING)
    private AlertSeverity severity;


    @Enumerated(EnumType.STRING)
    private AlertSource source;

    private LocalDateTime createdDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
}
