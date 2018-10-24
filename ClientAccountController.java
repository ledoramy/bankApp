package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ClientAccountController {

    private final ClientAccountRepository repository;
    private final BankAccountRepository bankRepository;
    private final BankLogRepository bankLogRepository;

    public ClientAccountController(ClientAccountRepository repository, BankAccountRepository bankRepository, BankLogRepository bankLogRepository) {
        this.repository = repository;
        this.bankRepository = bankRepository;
        this.bankLogRepository = bankLogRepository;

    }

    @RequestMapping("/buy/{id}/{amount}")
    public synchronized ClientAccount buyCoins(@PathVariable(value = "id") Integer acc, @PathVariable(value = "amount") Integer amount){
        ClientAccount ca = repository.findById(acc).get();
        BankAccount ba = bankRepository.findAll().get(0);

        if (amount > 0){
            OperationType operation = OperationType.BUY;
        BankLog log = new BankLog(operation.ordinal(), "BUY", String.valueOf(ca.getId()), String.valueOf(ba.getId()));
        ca.setAmount(ca.getAmount() + amount);
        ba.setAmount(ba.getAmount() - amount);
        bankLogRepository.save(log);
        bankRepository.save(ba);
        return repository.save(ca);}
        else return ca;

    }

    @RequestMapping("/transfer/{from}/{to}/{amount}")
    public synchronized ClientAccount transferCoins(@PathVariable(value = "from") Integer acc1, @PathVariable(value = "to") Integer acc2,
                                            @PathVariable(value = "amount") Integer amount){
        ClientAccount ca1 = repository.findById(acc1).get();
        ClientAccount ca2 = repository.findById(acc2).get();
        OperationType operation = OperationType.TRANSFER;
        BankLog log = new BankLog(operation.ordinal(), "TRANSFER", String.valueOf(ca2.getId()), String.valueOf(ca1.getId()));
        ca1.setAmount(ca1.getAmount() - amount);
        ca2.setAmount(ca2.getAmount() + amount);
        bankLogRepository.save(log);
        repository.save(ca2);
        return repository.save(ca1);

    }


}
