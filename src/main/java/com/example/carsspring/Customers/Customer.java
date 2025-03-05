package com.example.carsspring.Customers;

import com.example.carsspring.Dealership.Dealership;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

    private String name;
    private String email;
    private String phone;

    @ManyToMany
    @JoinTable(
            name = "customer_dealership",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "dealership_id")
    )
    private Set<Dealership> dealerships = new HashSet<>();

    public Customer() {}

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Set<Dealership> getDealerships() { return dealerships; }
    public void setDealerships(Set<Dealership> dealerships) { this.dealerships = dealerships; }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dealerships=" + dealerships +
                '}';
    }
}
