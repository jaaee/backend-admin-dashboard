package com.example.admin_dashboard.controller;

import com.example.admin_dashboard.dto.request.TransactionFilterRequest;
import com.example.admin_dashboard.dto.response.ChannelBreakdownResponse;
import com.example.admin_dashboard.dto.response.PagedResponse;
import com.example.admin_dashboard.dto.response.TransactionResponse;
import com.example.admin_dashboard.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping
    public List<TransactionResponse> getAllTransactions() {
        return transactionService.getAllTransactions();    }

    @GetMapping("/recent")
    public ResponseEntity<List<TransactionResponse>>
    getRecentTransactions() {

        return ResponseEntity.ok(
                transactionService.getRecentTransactions()
        );
    }

    @GetMapping("/channel-breakdown")
    public List<ChannelBreakdownResponse> getChannelBreakdown() {
        return transactionService.getChannelBreakdown();
    }

    @PostMapping("/filter")
    public PagedResponse<TransactionResponse>
    getFilteredTransactions(
            @RequestBody TransactionFilterRequest request) {

        return transactionService.getFilteredTransactions(request);

    }


    @PostMapping("/export")
    public ResponseEntity<byte[]> exportTransactions(
            @RequestBody TransactionFilterRequest request) {

        byte[] file = transactionService.exportTransactions(request);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=transactions.xlsx"
                )
                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM
                )
                .body(file);
    }

}
