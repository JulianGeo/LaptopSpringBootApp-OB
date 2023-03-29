package com.example.demo.entity;

import jakarta.persistence.*;


@Entity
@Table(name="Laptop")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String laptopID;
    @Column
    private String trademark;
    @Column
    private Double price;


    public Laptop(String trademark, Double price) {
        this.trademark=trademark;
        this.price=price;
    }

    public Laptop() {
    }

    public String getLaptopID() {
        return laptopID;
    }

    public void setLaptopID(String laptopID) {
        this.laptopID=laptopID;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark=trademark;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price=price;
    }
}
