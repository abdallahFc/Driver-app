package com.project.demo;

public class TwoPassengerDisscount extends Disscount {
    double price=0;
  public TwoPassengerDisscount() {
      System.out.println("Congratulation,You Have got A Two Passenger Disscount");
  }

  public double getDisscount(double price){
      return 0.95*price;
  }

}
