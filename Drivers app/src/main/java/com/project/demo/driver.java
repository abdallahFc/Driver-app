package com.project.demo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Ahmed Abdelnaser
 */
public class driver extends User implements Register, Login {

    HashMap<String, Integer> rating_List = new HashMap<>();
    ArrayList<String> favArea_List = new ArrayList<>();
    HashMap<String, String> requestList = new HashMap<>();
    LocalTime time = LocalTime.now();
    String Id;
    String license;
    String day = "Friday";
    float avgRate = 0;
    int sumRate = 0;
    int driverOffer = 0;
    boolean available = true;
    public static double  z=0;

    public driver(String natinalId, String license, String userName, String pohne, String password) {
        super(userName, pohne, password);
        this.Id = Id;
        this.license = license;
    }


    
    public boolean addFavArea(String d,String name)
    {
        boolean flag = true;
        for (int i = 0; i <= dataBase.getInstance().drivers_List.size(); i++) 
        {
            if (dataBase.getInstance().drivers_List.get(i).getUserName() == name) 
            {
                for (int j = 0; j < dataBase.getInstance().drivers_List.get(i).favArea_List.size(); j++) 
                {
                    if (d == dataBase.getInstance().drivers_List.get(i).favArea_List.get(j)) 
                    {
                        flag = false;
                        return flag;
                    }
                }
                if (flag)
                {
                    dataBase.getInstance().drivers_List.get(i).favArea_List.add(d);
                     return flag;
                } 
                else 
                    return flag;

            }

        }

        return false;
    }
	public ArrayList <String> Getlist(String name) {
		 for (int i=0;i<=dataBase.getInstance().drivers_List.size();i++) {
	   		 if(dataBase.getInstance().drivers_List.get(i).getUserName()==name) {
	   			return dataBase.getInstance().drivers_List.get(i).favArea_List;
	   		 }
	   		 
	   	 }
	       return null;
		
	}


    public String getId() {
        return Id;
    }

    public String getLincese() {
        return license;
    }

    public void setNatinalId(String natinalId) {
        this.Id = natinalId;
    }

    public void setLincese(String lincese) {
        this.license = lincese;
    }

    public Offer update(client c, String destination)
    {
        requestList.put(c.getUserName(), destination);
        
        System.out.println("Hey " + this.getUserName() +"! \n" + "Enter your offer");   
        Scanner in  = new Scanner(System.in);     
        driverOffer = in.nextInt();
        z=driverOffer;
        Offer offer = new Offer(driverOffer, this.getUserName(), this.getPohne());
        return discount(offer, destination, c);
     
    }
    public Offer discount(Offer offer, String destination, client c)
    {
        LocalDate currentDate = LocalDate.now();  
        
        if (c.getFirstRide())
        {   
            FirstRideDisscount first = new FirstRideDisscount();
            offer.setPrice(first.getDisscount(offer.getPrice())); /// FIRST RIDE  1
         }
        
        if(c.birthDay == currentDate.getDayOfMonth() && c.birthMonth == currentDate.getMonth())  /// BIRTHDAY  2
        { 
            BirthDateDisscount birth = new BirthDateDisscount();
            offer.setPrice(birth.getDisscount(offer.getPrice()));
        
        }
        
        if (c.takeMoreThanone) /// TAKE MORE THATN ONE PASSNGER  3
        {
            TwoPassengerDisscount passenger = new TwoPassengerDisscount();
            offer.setPrice(passenger.getDisscount(offer.getPrice()));
        }
        
        for(int i =0; i <Admin.getInstance().getAres().size(); i++)  /// AREAS ADDED BY ADMIN   4
        {
            if (Admin.getInstance().getAres().get(i).equalsIgnoreCase(destination))
            {
                 AreaAddByAdmin adminArea = new AreaAddByAdmin();
                 offer.setPrice(adminArea.getDisscount(offer.getPrice()));
            }
        }
        
        for(int i =0; i <dataBase.getInstance().Holidays.size(); i++)       /// PUBLIC HOLIDAYS  5
        {
            if (dataBase.getInstance().getDay().equalsIgnoreCase(day))
            {
                 PublicHolidayDisscount holiday = new PublicHolidayDisscount();
                 offer.setPrice(holiday.getDisscount(offer.getPrice()));
            }
        }
        
        return offer;
        
    }

    @Override
    public boolean Register() //// API
    {
        for (int i = 0; i < dataBase.getInstance().drivers_List.size(); i++) {
            if (dataBase.getInstance().drivers_List.get(i).userName.equals(this.userName)) {
                System.out.println("username is taken");
                return false;
            }
        }

        dataBase.getInstance().pendingDrivers.add(this);
        Admin.getInstance().verify(this);
        return true;
    }

    @Override
    public driver login(String name, String pass) {
    	driver d=Admin.getInstance().validateDetilesDriver(name, pass);
        if (d!=null) {
            System.out.println("Driver Login Successfully");
            return d;
        } else {
        
            return null;
        }

    }

    public void setAvg(float avg_rate) {
        this.avgRate = avg_rate;
    }

    public void printRate() {
        for (String i : rating_List.keySet()) {
            System.out.println(i + " " + rating_List.get(i));
        }

    }
    public HashMap<String, Integer> getRate() {

		return rating_List;
    }

    public float getAvgRate() {
        return avgRate;
    }

    public HashMap getRequest()
    {
        return requestList;
    }
    
    public ArrayList getFav()
    {
        return favArea_List;
    }
    
    public String setAcceptedClient(client c,double p)
    {
        Events e = new Events("Start Trip", this.userName, c.userName, time,z,p);
        Events x = new Events("Arrival", this.userName, c.userName, time.plusMinutes(5),z,p);
        Events a = new Events("Trip End", this.userName, c.userName, time.plusMinutes(10),z,p);
        this.available=true;
        return(e.toString())+(a.toString())+(x.toString());
    }

}


	