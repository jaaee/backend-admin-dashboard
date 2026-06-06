package com.example.admin_dashboard.mapper;

import com.example.admin_dashboard.dto.response.TransactionResponse;
import com.example.admin_dashboard.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionResponse toResponse(Transaction transaction) {

        return TransactionResponse.builder()
                .id(transaction.getId())
                .referenceNo(transaction.getReferenceNo())
                .customerName(transaction.getUser().getUserName())
                .amount(transaction.getAmount())
                .status(transaction.getStatus())
                .riskLevel(transaction.getRiskLevel())
                .type(transaction.getType())
                .channel(transaction.getChannel().getChannelName())
                .currency(transaction.getCurrency())
                .riskScore(transaction.getRiskScore())
                .build();
    }
}
