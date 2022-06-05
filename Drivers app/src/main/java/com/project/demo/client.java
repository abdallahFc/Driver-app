package com.project.demo;

import java.time.LocalTime;
import java.time.Month;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Ahmed Abdelnaser
 * @author Omar Abdallah 20190771
 * @author Abdallah Mohamed 20190327
 * @author Nour Hosny 20190589
 */
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Ahmed Abdelnaser
 * @author Omar Abdallah 20190771
 * @author Abdallah Mohamed 20190327
 * @author Nour Hosny 20190589
 */
public class client extends User implements Register, Login {
	LocalTime time = LocalTime.now();
    int birthDay = 2;
    Month birthMonth = Month.JANUARY;
    boolean takeMoreThanone = false;
    private boolean firstRide = true;
	double f;
    private ArrayList<Offer> offerList = new ArrayList<>();

    public boolean getFirstRide() {
        return firstRide;
    }

    public client(String userName, String pohne, String password) {
        super(userName, pohne, password);

    }

    public boolean hasRight() {
        for (int i = 0; i < dataBase.getInstance().clients_List.size(); i++) {
            if (dataBase.getInstance().clients_List.get(i).equals(this)) {
                return true;
            }
        }
        return false;
    }

    public void request(String src, String des) {
        if (this.hasRight()) {

            System.out.println("Do you like to take someone with you in this ride? (y/n)");   //ASK IF HE WANNA MORE THAN ONE WITH HIM
            Scanner f = new Scanner(System.in);
            String option = f.next();
            if (option.equalsIgnoreCase("y")) {
                this.takeMoreThanone = true;
            }

            ride r = new ride(src, des, this, offerList);
            if (this.offerList.size() == 0) {
                System.out.println("Out of Scope!!");
            } else {

                System.out.println("you have a new offer, check your list!");
            }
        } else {
            System.out.println("You're suspended or not registered");
        }
    }
    public ArrayList<Offer> getOffers() {

        return offerList;
    }
    public void getEvents() {

        for(int i=0 ; i<offerList.size();i++)
        {
        	f=driver.z;
            Events e=new Events("Possible Trip",offerList.get(i).getDriverName(),this.userName,time,f,offerList.get(i).getPrice());
            Admin.getInstance().EventsHistory.add(e);
        }
    }
    public ArrayList<Events> PrintEvents() {
        return Admin.getInstance().EventsHistory;
    }

    @Override
    public boolean Register() {
        for (int i = 0; i < dataBase.getInstance().clients_List.size(); i++) {
            if (dataBase.getInstance().clients_List.get(i).userName.equals(this.userName)) {
                System.out.println("username is taken");
                return false;
            }
        }
        dataBase.getInstance().clients_List.add(this);
        System.out.println("Client Registered Successfully");
        return true;
    }

    @Override
    public client login(String name, String pass) {
    	client c=Admin.getInstance().validateDetilesClient(name, pass);
        if (c!=null) {
            System.out.println("Client Login Successfully");
            return c;
        } else {
        
            return null;
        }

    }

    public void giveRate(int r, String driverName) {
        if (this.hasRight()) {
            Rate rate = new Rate(r, driverName, this);
        } else {
            System.out.println("You'r not registered");
        }
    }
    
    public String chooseOffer() {
        String name;
        this.getEvents();
        System.out.println(this.PrintEvents());
        System.out.println("Enter the driver's name you want to accept: ");
        Scanner in = new Scanner(System.in);
        name = in.next();
        if (!checkDriver(name)) {
            System.out.println("Invalid name!!");
        } else {
            for (int i = 0; i < dataBase.getInstance().drivers_List.size(); i++) {
                if (dataBase.getInstance().drivers_List.get(i).getUserName().equals(name)
                        && dataBase.getInstance().drivers_List.get(i).available) {
                	firstRide = false;
                    return this.PrintEvents()+dataBase.getInstance().drivers_List.get(i).setAcceptedClient(this,offerList.get(i).getPrice());
                    
                }
            }
        }
		return null;

    }
    
    public boolean checkDriver(String n)
    {
        for (int i=0; i<offerList.size(); i++)
        {
            if (n.equals(offerList.get(i).getDriverName()))
                return true;
            
        }
        return false;
    }
}