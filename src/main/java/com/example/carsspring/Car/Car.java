package com.example.carsspring.Car;

import com.example.carsspring.Dealership.Dealership;
import jakarta.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;
    private String color;
    private int year;
    private int price;

    @ManyToOne
    @JoinColumn(name = "dealership_id", nullable = false)
    private Dealership dealership;

    public Car() {
    }

    public Car(Long id, String make, String color, int year, int price, Dealership dealership) {
        this.id = id;
        this.make = make;
        this.color = color;
        this.year = year;
        this.price = price;
        this.dealership = dealership;
    }

    public Car(String make, String color, int year, int price, Dealership dealership) {
        this.make = make;
        this.color = color;
        this.year = year;
        this.price = price;
        this.dealership = dealership;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Dealership getDealership() {
        return dealership;
    }

    public void setDealership(Dealership dealership) {
        this.dealership = dealership;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", color='" + color + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", dealership=" + (dealership != null ? dealership.getDealerName() : "None") +
                '}';
    }
}
