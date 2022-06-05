package com.project.demo;

import java.time.LocalTime;

public class Events {
    public String EventName,DriverName,ClientName;
    LocalTime EventTime;
    double price=0;
    double priceAfterDiscount=0;
    public String getEventName() {
        return EventName;
    }

    public void setEventName(String EventName) {
        this.EventName = EventName;
    }

    public LocalTime getEventTime() {
        return EventTime;
    }

    public void setEventTime(LocalTime EventTime) {
        this.EventTime = EventTime;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String DriverName) {
        this.DriverName = DriverName;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String ClientName) {
        this.ClientName = ClientName;
    }

    public Events() {
    }

    public Events(String EventName, String DriverName, String ClientName, LocalTime EventTime,double price, double priceAfterDiscount) {
        this.EventName = EventName;
        this.DriverName = DriverName;
        this.ClientName = ClientName;
        this.EventTime = EventTime;
        this.price=price;
        this.priceAfterDiscount=priceAfterDiscount;
    }

    @Override
    public String toString() {
    	return "{" + "EventName = " + EventName + ", DriverName = " + DriverName + ", ClientName = " + ClientName + ", EventTime = " + EventTime + ", Trip Price Before Disscount = " + price +',' + "Trip Price After Disscount = " + priceAfterDiscount +'}' ;
    }

	public double getPriceAfterDiscount() {
		return priceAfterDiscount;
	}

	public void setPriceAfterDiscount(double priceAfterDiscount) {
		this.priceAfterDiscount = priceAfterDiscount;
	}

 
    
    
    
}
