package com.project.demo;

public class BirthDateDisscount extends Disscount {

    public BirthDateDisscount()
    {
      System.out.println("Congratulation,You Have got A  birthday discount!!");
  }

  public double getDisscount(double price){
      
      return 0.9*price;
  }

}
