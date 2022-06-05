package com.project.demo;

public class FirstRideDisscount extends Disscount{
	   
    public FirstRideDisscount() {
        System.out.println("Congratulation,You Have got A first ride discount!!");
    }

    public double getDisscount(double price){
        
        return 0.9*price;
    }

}
