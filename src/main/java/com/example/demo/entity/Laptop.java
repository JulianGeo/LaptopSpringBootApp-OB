package com.example.demo.entity;

import jakarta.persistence.*;


@Entity
@Table(name="Laptop")
public class Laptop {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String laptopID;
    @Column
    private String tradeMark;


    public Laptop(String laptopID, String tradeMark) {
        this.laptopID=laptopID;
        this.tradeMark=tradeMark;
    }

    public Laptop() {
    }

    public String getLaptopID() {
        return laptopID;
    }

    public void setLaptopID(String laptopID) {
        this.laptopID=laptopID;
    }

    public String getTradeMark() {
        return tradeMark;
    }

    public void setTradeMark(String tradeMark) {
        this.tradeMark=tradeMark;
    }
}
