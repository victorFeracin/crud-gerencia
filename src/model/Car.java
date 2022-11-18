/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author vhsf0
 */
public class Car implements java.io.Serializable {
    private static long serialVersionUID = 1L;
    private int idCar;
    private String name;
    private int year;
    private String brand;
    private double price;

    //Getters
    public int getIdCar() {
        return idCar;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }
    
    
    //Setters
    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    //Constructor
    public Car() {
        
    }

    public Car(int idCar, String name, int year, String brand, double price) {
        this.idCar = idCar;
        this.name = name;
        this.year = year;
        this.brand = brand;
        this.price = price;
    }
}