package com.example.admin_dashboard.service;

import com.example.admin_dashboard.dto.request.TransactionFilterRequest;
import com.example.admin_dashboard.dto.response.ChannelBreakdownResponse;
import com.example.admin_dashboard.dto.response.PagedResponse;
import com.example.admin_dashboard.dto.response.TransactionResponse;
import com.example.admin_dashboard.enums.RiskLevel;
import com.example.admin_dashboard.enums.TransactionStatus;
import com.example.admin_dashboard.mapper.ChannelBreakdownMapper;
import com.example.admin_dashboard.mapper.TransactionMapper;
import com.example.admin_dashboard.model.Transaction;
import com.example.admin_dashboard.projection.ChannelBreakdownProjection;
import com.example.admin_dashboard.projection.RiskAnalysisProjection;
import com.example.admin_dashboard.projection.TransactionMetricsProjection;
import com.example.admin_dashboard.projection.TransactionTrendProjection;
import com.example.admin_dashboard.repository.TransactionRepository;
import com.example.admin_dashboard.specification.TransactionFilterSpecification;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionMapper transactionMapper;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ChannelBreakdownMapper channelBreakdownMapper;

    public List<TransactionResponse> getAllTransactions(){
        return transactionRepository.findAll()
                .stream()
                .map(transactionMapper::toResponse)
                .toList();
    }


    public PagedResponse<TransactionResponse> getRecentTransactions(int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );

        Page<Transaction> transactionPage =
                transactionRepository.findAll(
                        pageable
                );

        List<TransactionResponse> transactions =
                transactionPage.getContent()
                        .stream()
                        .map(transactionMapper::toResponse)
                        .toList();

        return PagedResponse.<TransactionResponse>builder()
                .content(transactions)
                .pageNumber(transactionPage.getNumber())
                .pageSize(transactionPage.getSize())
                .totalElements(transactionPage.getTotalElements())
                .totalPages(transactionPage.getTotalPages())
                .last(transactionPage.isLast())
                .build();

//        return transactionRepository.findTop10ByOrderByCreatedAtDesc()
//                .stream()
//                .map(transactionMapper::toResponse)
//                .toList();
    }

    public List<ChannelBreakdownResponse> getChannelBreakdown() {

        List<ChannelBreakdownProjection> channels =
                transactionRepository.getChannelBreakdown();

        double grandTotal = channels.stream()
                .mapToDouble(ChannelBreakdownProjection::getTotalAmount)
                .sum();

        return channels.stream()
                .map(channel ->
                        channelBreakdownMapper.toResponse(
                                channel,
                                grandTotal
                        ))
                .toList();
    }

    @Override
    public long getTotalTransactions() {
        return transactionRepository.count();
    }

    @Override
    public long getPendingTransactions() {
        return transactionRepository.countByStatusIn(
                List.of(
                        TransactionStatus.PENDING,
                        TransactionStatus.PROCESSING));

    }

    @Override
    public long getFailedTransactions() {
        return transactionRepository
                .countByStatus(TransactionStatus.FAILED);
    }

    @Override
    public long getHighRiskTransactions() {
        return transactionRepository
                .countByRiskLevelIn(
                        List.of(
                                RiskLevel.HIGH,
                                RiskLevel.CRITICAL
                        )
                );
    }

    @Override
    public long getSuccessfulTransactions(){
        return transactionRepository
                .countByStatus(TransactionStatus.SUCCESS);
    }


    @Override
    public TransactionMetricsProjection getChangeInTransactions(){
        return transactionRepository.getChangeInTransactions();
    }

    @Override
    public List<TransactionTrendProjection>getTransactionTrends(){
        return transactionRepository.getTransactionTrends();
    }
    @Override
    public List<RiskAnalysisProjection> getRiskAnalysis() {
        return transactionRepository.getRiskAnalysis();
    }

    @Override
    public PagedResponse<TransactionResponse> getFilteredTransactions(
            TransactionFilterRequest request){
        Sort sort = Sort.by(
                Sort.Direction.fromString(
                        request.getSortOrder()),
                request.getSortField()
        );

        Pageable pageable = PageRequest.of(
                request.getPageNumber(),
                request.getPageSize(),
                sort
        );

        Page<Transaction> transactionPage =
                transactionRepository.findAll(

                    TransactionFilterSpecification.filter(
                                request.getSearchKeyword(),
                                request.getStatus(),
                                request.getType(),
                                request.getChannel()
                        ),

                        pageable
                );

        List<TransactionResponse> transactions =
                transactionPage.getContent()
                        .stream()
                        .map(transactionMapper::toResponse)
                        .toList();

        return PagedResponse.<TransactionResponse>builder()
                .content(transactions)
                .pageNumber(transactionPage.getNumber())
                .pageSize(transactionPage.getSize())
                .totalElements(transactionPage.getTotalElements())
                .totalPages(transactionPage.getTotalPages())
                .last(transactionPage.isLast())
                .build();
    }

    @Override
    public byte[] exportTransactions(
            TransactionFilterRequest request) {

        List<Transaction> transactions =
                transactionRepository.findAll(
                        TransactionFilterSpecification.filter(
                                request.getSearchKeyword(),
                                request.getStatus(),
                                request.getType(),
                                request.getChannel()
                        )
                );

        return generateExcel(transactions);
    }

    private byte[] generateExcel(
            List<Transaction> transactions) {

        try (
                Workbook workbook =
                        new XSSFWorkbook();
                ByteArrayOutputStream outputStream =
                        new ByteArrayOutputStream()
        ) {

            Sheet sheet =
                    workbook.createSheet(
                            "Transactions"
                    );

            createHeader(sheet);

            int rowNumber = 1;

            for (Transaction transaction :
                    transactions) {

                Row row =
                        sheet.createRow(rowNumber++);

                row.createCell(0)
                        .setCellValue(
                                transaction.getId()
                        );

                row.createCell(1)
                        .setCellValue(
                                transaction.getReferenceNo()
                        );

                row.createCell(2)
                        .setCellValue(
                                transaction.getUser().getUserName()
                        );

                row.createCell(3)
                        .setCellValue(
                                transaction.getAmount()
                                        .doubleValue()
                        );

                row.createCell(4)
                        .setCellValue(
                                transaction.getStatus()
                                        .name()
                        );

                row.createCell(5)
                        .setCellValue(
                                transaction.getType()
                                        .name()
                        );

                row.createCell(6)
                        .setCellValue(
                                transaction.getChannel().getChannelName()
                        );

                row.createCell(7)
                        .setCellValue(
                                transaction.getCreatedAt()
                                        .toString()
                        );
            }

            for (int i = 0; i < 8; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);

            return outputStream.toByteArray();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to export transactions",
                    e
            );
        }
    }

    private void createHeader(
            Sheet sheet) {

        Row header =
                sheet.createRow(0);

        header.createCell(0)
                .setCellValue("Transaction ID");

        header.createCell(1)
                .setCellValue("Reference No");

        header.createCell(2)
                .setCellValue("User ID");

        header.createCell(3)
                .setCellValue("Amount");

        header.createCell(4)
                .setCellValue("Status");

        header.createCell(5)
                .setCellValue("Type");

        header.createCell(6)
                .setCellValue("Channel");

        header.createCell(7)
                .setCellValue("Created At");
    }

    @Override
    public long getTransactionsPerSecond() {

        Long count =
                transactionRepository.countTransactionsAfter(
                        LocalDateTime.now().minusMinutes(1)
                );

        return count / 60;
    }

    @Override
    public long getPendingTransactionsCount() {

        return this.getPendingTransactions();
    }


}




