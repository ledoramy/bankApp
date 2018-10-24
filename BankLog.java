package com.example.demo;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name="bank_log")
public class BankLog {

    @Id
    @GeneratedValue
    private int id;

    private int operationId;
    private String operationDescription;

    private String recipient;
    private String sender;

    @Temporal(TemporalType.DATE)
    public LocalDate getOperationDateTime() {
        return operationDateTime;
    }

    public void setOperationDateTime(LocalDate operationDateTime) {
        this.operationDateTime = operationDateTime;
    }

    private LocalDate operationDateTime;

    BankLog(){
    }

    public BankLog(int operationId, String operationDescription, String recipient, String sender) {
        this.operationId = operationId;
        this.operationDescription = operationDescription;
        this.recipient = recipient;
        this.sender = sender;
        this.operationDateTime = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


}
