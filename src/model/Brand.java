/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author vhsf0
 */
public class Brand implements java.io.Serializable {
    private static long serialVersionUID = 1L;
    private int idBrand;
    private String name;
    private int yearCreated;
    private String website;
    
    //getters
    public int getIdBrand() {
        return idBrand;
    }

    public String getName() {
        return name;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public String getWebsite() {
        return website;
    }
    
    //setters
    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    
    //constructor
    public Brand() {
        
    }

    public Brand(int idBrand, String name, int yearCreated, String website) {
        this.idBrand = idBrand;
        this.name = name;
        this.yearCreated = yearCreated;
        this.website = website;
    }
 
}