package com.project.demo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author omarb
 */
public class Offer {
    
    private double price =0;
    private String driverName = " ";
    private String phone;
    public Offer() {}
    public Offer (int p, String n, String ph)
    {
        price = p;
        driverName = n; 
        phone = ph;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public double getPrice()
    {
        return price;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Offer{" + "price=" + price + ", driverName=" + driverName + ", phone=" + phone + '}';
    }
    
    
}

