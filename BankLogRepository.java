package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface BankLogRepository extends JpaRepository<BankLog, Integer> {

    List<BankLog> findAllByRecipient(String id);
    List<BankLog> findAllBySender(String id);

    @Query("SELECT l FROM bank_log as l where l.recipient = :id and l.operationDateTime between :from and :to")
    List<BankLog> findAllByRecipientAndOperationDateTimeWithin(@Param("id") String id, @Param("from") LocalDate dateFrom, @Param("to") LocalDate dateTo);

    @Query("SELECT l FROM bank_log as l where l.sender = :id and l.operationDateTime between :from and :to")
    List<BankLog> findAllBySenderAndOperationDateTimeWithin(@Param("id") String id, @Param("from") LocalDate dateFrom, @Param("to") LocalDate dateTo);

    @Query("SELECT l FROM bank_log as l, client_acc as ca where l.sender = ca.id and l.operationDateTime between :from " +
            "and :to and ca.owner = :id" )
    List<BankLog> findAllBySenderClientIdAndOperationDateTimeBetween(@Param("id") String id, @Param("from") LocalDate dateFrom, @Param("to")LocalDate dateTo);

    @Query("SELECT l FROM bank_log as l, client_acc as ca where l.recipient = ca.id and l.operationDateTime between :from " +
            "and :to and ca.owner = :id" )
    List<BankLog> findAllByRecipientClientIdAndOperationDateTimeBetween(@Param("id") String id, @Param("from") LocalDate dateFrom, @Param("to")LocalDate dateTo);

    @Query("SELECT l FROM bank_log l, client_acc as ca where l.recipient = ca.id and ca.owner = :id" )
    List<BankLog> findAllByRecipientClientId(@Param("id") String id);

    @Query("SELECT l FROM bank_log as l, client_acc as ca where l.sender = ca.id and ca.owner = :id" )
    List<BankLog> findAllBySenderClientId(@Param("id") String id);

}
