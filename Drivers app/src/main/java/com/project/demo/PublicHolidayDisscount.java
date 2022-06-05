package com.project.demo;

public class PublicHolidayDisscount extends Disscount {
    double price=0;
    public PublicHolidayDisscount() {
        System.out.println("Congratulation,You Have got A Public Holiday Disscount");
    }

    public double getDisscount(double price){
        return 0.95*price;
    }


}
