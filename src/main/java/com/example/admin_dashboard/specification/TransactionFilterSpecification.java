package com.example.admin_dashboard.specification;

import com.example.admin_dashboard.enums.TransactionStatus;
import com.example.admin_dashboard.enums.TransactionType;
import com.example.admin_dashboard.model.Channel;
import com.example.admin_dashboard.model.Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class TransactionFilterSpecification {
    private TransactionFilterSpecification() {
    }

    public static Specification<Transaction> filter(
            String searchKeyword,
            TransactionStatus status,
            TransactionType type,
            Channel channel
    ) {

//        return (root, query, criteriaBuilder) -> {
//
//            List<Predicate> predicates = new ArrayList<>();
//
//            // Search
//            if (searchKeyword != null &&
//                    !searchKeyword.trim().isEmpty()) {
//
//                String search =
//                        "%" + searchKeyword.toLowerCase() + "%";
//
//                predicates.add(
//
//                        criteriaBuilder.or(
//
//                                criteriaBuilder.like(
//                                        criteriaBuilder.lower(
//                                                root.get("id")
//                                        ),
//                                        search
//                                ),
//
//                                criteriaBuilder.like(
//                                        criteriaBuilder.lower(
//                                                root.get("customerName")
//                                        ),
//                                        search
//                                )
//
//                        )
//                );
//            }
//
//            // Status Filter
//            if (status != null) {
//
//                predicates.add(
//                        criteriaBuilder.equal(
//                                root.get("status"),
//                                status
//                        )
//                );
//            }
//
//            // Type Filter
//            if (type != null) {
//
//                predicates.add(
//                        criteriaBuilder.equal(
//                                root.get("type"),
//                                type
//                        )
//                );
//            }
//
//            // Channel Filter
//            if (channel != null) {
//
//                predicates.add(
//                        criteriaBuilder.equal(
//                                root.get("channel"),
//                                channel
//                        )
//                );
//            }
//
//            return criteriaBuilder.and(
//                    predicates.toArray(new Predicate[0])
//            );
//        };


        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            // Search
//            if (searchKeyword != null &&
//                    !searchKeyword.trim().isEmpty()) {
//
//                String search =
//                        "%" + searchKeyword.toLowerCase() + "%";
//
//                List<Predicate> searchPredicates = new ArrayList<>();
//
//                // Search by customer name
//                searchPredicates.add(
//                        criteriaBuilder.like(
//                                criteriaBuilder.lower(
//                                        root.get("customerName")
//                                ),
//                                search
//                        )
//                );
//
//                // Search by ID only if numeric
//                if (searchKeyword.matches("\\d+")) {
//
//                    searchPredicates.add(
//                            criteriaBuilder.equal(
//                                    root.get("id"),
//                                    Long.valueOf(searchKeyword)
//                            )
//                    );
//                }
//
//                predicates.add(
//                        criteriaBuilder.or(
//                                searchPredicates.toArray(new Predicate[0])
//                        )
//                );
//            }

            if (searchKeyword != null &&
                    !searchKeyword.trim().isEmpty()) {

                String search =
                        "%" + searchKeyword.toLowerCase().trim() + "%";

                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(
                                        root.get("referenceNo")
                                ),
                                search
                        )
                );
            }

            // Status Filter
            if (status != null) {

                predicates.add(
                        criteriaBuilder.equal(
                                root.get("status"),
                                status
                        )
                );
            }

            // Type Filter
            if (type != null) {

                predicates.add(
                        criteriaBuilder.equal(
                                root.get("type"),
                                type
                        )
                );
            }

            // Channel Filter
            if (channel != null) {

                predicates.add(
                        criteriaBuilder.equal(
                                root.get("channel"),
                                channel
                        )
                );
            }

            return criteriaBuilder.and(
                    predicates.toArray(new Predicate[0])
            );
        };
    }
}
