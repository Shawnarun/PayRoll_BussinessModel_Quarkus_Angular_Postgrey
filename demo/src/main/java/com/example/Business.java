package com.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Business {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100)
    private String Name;
    @Column(length = 100)
    private String LName;
    @Column(length = 100)
    private int ABN_Number;
    @Column(length = 100)
    private int ACN_Number;

    public Business(Long id, String name, String LName, int ABN_Number, int ACN_Number) {
        this.id = id;
        this.Name = name;
        this.LName = LName;
        this.ABN_Number = ABN_Number;
        this.ACN_Number = ACN_Number;
    }

    public Business(String name, String LName, int ABN_Number, int ACN_Number) {
        this.Name = name;
        this.LName = LName;
        this.ABN_Number = ABN_Number;
        this.ACN_Number = ACN_Number;
    }

    public Business() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public int getABN_Number() {
        return ABN_Number;
    }

    public void setABN_Number(int ABN_Number) {
        this.ABN_Number = ABN_Number;
    }

    public int getACN_Number() {
        return ACN_Number;
    }

    public void setACN_Number(int ACN_Number) {
        this.ACN_Number = ACN_Number;
    }
}

