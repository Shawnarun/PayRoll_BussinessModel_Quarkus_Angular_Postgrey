package com.example.DTO;

public class BusinessUpdateDTO {

    private Long id;
    private String Name;
    private String LName;
    private int ABN_Number;
    private int ACN_Number;

    public BusinessUpdateDTO(Long id, String name, String LName, int ABN_Number, int ACN_Number) {
        this.id = id;
        Name = name;
        this.LName = LName;
        this.ABN_Number = ABN_Number;
        this.ACN_Number = ACN_Number;
    }

    public BusinessUpdateDTO() {
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

    @Override
    public String toString() {
        return "BusinessUpdateDTO{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", LName='" + LName + '\'' +
                ", ABN_Number=" + ABN_Number +
                ", ACN_Number=" + ACN_Number +
                '}';
    }
}
