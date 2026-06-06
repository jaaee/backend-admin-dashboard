package com.example.admin_dashboard.dto.response;

import com.example.admin_dashboard.enums.AlertSeverity;
import com.example.admin_dashboard.enums.AlertSource;
import com.example.admin_dashboard.enums.AlertType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AlertResponse {

    private Long id;

    private String alertCode;

    private String title;

    private String message;

    private AlertType type;

    private AlertSeverity severity;

    private AlertSource source;

    private LocalDateTime createdDate;

    private String transactionRefNo;

}
