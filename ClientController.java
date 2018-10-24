package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class ClientController {

    private final ClientsDataRestRepository repository;

    public ClientController(ClientsDataRestRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/clients")
    public List<Client> getAllClients(){
        return repository.findAll();
    }

    @RequestMapping("/clients/{id}")
    public Client getClientById(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    @RequestMapping("/accounts/{id}")
    public Set<ClientAccount> getAccountsById(@PathVariable Integer id){
        Client c = repository.findById(id).get();
        return c.getAccounts();
    }

}
