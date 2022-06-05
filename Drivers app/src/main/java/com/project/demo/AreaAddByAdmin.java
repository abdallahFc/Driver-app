package com.project.demo;

public class AreaAddByAdmin extends Disscount {
    double price=0;
    public AreaAddByAdmin() {
        System.out.println("Congratulation,You Have got A Area Add By Admin Disscount");
    }

    public double getDisscount(double price){
        return 0.9*price;
    }
}
