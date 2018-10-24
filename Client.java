package com.example.demo;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String phone;


    public Set<ClientAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<ClientAccount> accounts) {
        this.accounts = accounts;
    }

    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    private Set<ClientAccount> accounts;

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
