package com.example.demo;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankAccountController {

    private final BankAccountRepository repository;
    private final BankLogRepository bankLogRepository;

    public BankAccountController(BankAccountRepository repository, BankLogRepository bankLogRepository) {
        this.repository = repository;
        this.bankLogRepository = bankLogRepository;
    }

    @RequestMapping("/bank")
    public BankAccount getAllInfo(){
        return repository.findAll().get(0);
    }

    @GetMapping("/emit/{amount}")
    public synchronized BankAccount emitCoins(@PathVariable(value = "amount", required = true) Integer amount){

            if (amount > 0) {
                BankAccount account = repository.findAll().get(0);
                account.setAmount(account.getAmount() + amount);
                OperationType operation = OperationType.EMISSION;
                BankLog log = new BankLog(operation.ordinal(), operation.toString(), String.valueOf(account.getId()), "");
                return repository.save(account);

            } else return repository.findAll().get(0);

    }


}
