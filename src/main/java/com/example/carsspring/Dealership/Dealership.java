package com.example.carsspring.Dealership;

import com.example.carsspring.Car.Car;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Dealership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dealerName;
    private String dealLoc;

    @OneToMany(mappedBy = "dealership", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Car> cars = new HashSet<>();

    public Dealership() {
    }

    public Dealership(Long id, String dealerName, String dealLoc) {
        this.id = id;
        this.dealerName = dealerName;
        this.dealLoc = dealLoc;
    }

    public Dealership(String dealerName, String dealLoc) {
        this.dealerName = dealerName;
        this.dealLoc = dealLoc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealLoc() {
        return dealLoc;
    }

    public void setDealLoc(String dealLoc) {
        this.dealLoc = dealLoc;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Dealership{" +
                "id=" + id +
                ", dealerName='" + dealerName + '\'' +
                ", dealLoc='" + dealLoc + '\'' +
                ", cars=" + cars +
                '}';
    }
}
