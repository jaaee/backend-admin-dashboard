package com.example.admin_dashboard.dto.response;

import com.example.admin_dashboard.annotation.LogMasked;
import com.example.admin_dashboard.enums.Currency;
import com.example.admin_dashboard.enums.RiskLevel;
import com.example.admin_dashboard.enums.TransactionStatus;
import com.example.admin_dashboard.enums.TransactionType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {

    private Long id;
    private String referenceNo;
    @LogMasked
    private String customerName;
    private Double amount;
    private TransactionStatus status;
    private RiskLevel riskLevel;
    private String channel;
    private Currency currency;
    private TransactionType type;
    private Float riskScore;
}