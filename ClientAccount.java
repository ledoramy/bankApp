package com.example.demo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity(name = "client_acc")
public class ClientAccount {

    @Id
    @GeneratedValue
    int id;

    int amount;

    @ManyToOne
    @JoinColumn(name = "owner")
    @JsonBackReference
    private Client owner;

    public ClientAccount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }
}
