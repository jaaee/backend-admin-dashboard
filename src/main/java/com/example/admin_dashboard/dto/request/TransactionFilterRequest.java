package com.example.admin_dashboard.dto.request;

import com.example.admin_dashboard.enums.TransactionStatus;
import com.example.admin_dashboard.enums.TransactionType;
import com.example.admin_dashboard.model.Channel;
import lombok.Data;

@Data
public class TransactionFilterRequest {

    private String searchKeyword;

    private TransactionStatus status;

    private TransactionType type;

    private Channel channel;

    private Integer pageNumber = 0;

    private Integer pageSize = 10;

    private String sortField = "createdAt";

    private String sortOrder = "DESC";
}
