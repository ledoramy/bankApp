package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientAccountRepository extends JpaRepository<ClientAccount, Integer> {
    public Client findClientById(Integer id);
}
