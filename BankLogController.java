package com.example.demo;

import org.springframework.data.repository.query.Param;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class BankLogController {

    private final BankLogRepository repository;

    public BankLogController(BankLogRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/showlog/")
    public List<BankLog> showLog() {
        return repository.findAll();
    }

    @RequestMapping("/showlog/account/{id}")
    public List<BankLog> showLogByAccountId(@PathVariable String id) {
        List<BankLog> senderLog = repository.findAllBySender(id);

        List<BankLog> recipientLog = repository.findAllByRecipient(id);
        for (BankLog l : recipientLog) {
            senderLog.add(l);
        }

        return senderLog;
    }

    @RequestMapping("/showlog/client/{id}")
    public List<BankLog> showLogByClientId(@PathVariable String id) {
        List<BankLog> recipientLog = repository.findAllByRecipientClientId(id);
        List<BankLog> senderLog = repository.findAllBySenderClientId(id);
        for (BankLog l : senderLog) {
            recipientLog.add(l);
        }
        return recipientLog;
    }

    @RequestMapping("/showlog/client/{id}/{from}/{to}")
    public List<BankLog> showLogByClientIdAndDate(@PathVariable(value = "id") String id, @PathVariable(value = "from") String dateFrom,
                                                  @PathVariable(value = "to") String dateTo)

    {
        LocalDate from = LocalDate.parse(dateFrom);
        LocalDate to = LocalDate.parse(dateTo);
        List<BankLog> recipientLog = repository.findAllByRecipientClientIdAndOperationDateTimeBetween(id, from, to);
        List<BankLog> senderLog = repository.findAllBySenderClientIdAndOperationDateTimeBetween(id, from, to);
        for (BankLog l : senderLog){
            recipientLog.add(l);
        }
        return recipientLog;

    }

    @RequestMapping("/showlog/account/{id}/{from}/{to}")
    public List<BankLog> showLogByAccountIdAndDate(@PathVariable(value = "id") String id,
                                                   @PathVariable(value = "from") String dateFrom,
                                                    @PathVariable(value = "to") String dateTo){

       // DateTimeFormatter df = DateTimeFormatter.ISO_DATE;
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate to = LocalDate.parse( dateTo, DateTimeFormatter.BASIC_ISO_DATE);

        List<BankLog> recipientLog = repository.findAllByRecipientAndOperationDateTimeWithin(id, from, to);
        List<BankLog> senderLog = repository.findAllBySenderAndOperationDateTimeWithin(id, from, to);
        for (BankLog l : senderLog)
        {
            recipientLog.add(l);
        }
        return recipientLog;
    }


}
