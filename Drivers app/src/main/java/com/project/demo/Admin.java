package com.project.demo;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
@author Ahmed Abdelnaser
 * @author Omar Abdallah 20190771
 * @author Abdallah Mohamed 20190327
 * @author Nour Hosny 20190589
 */
public class Admin extends User {
    
    
    private static Admin admin = null;
    public ArrayList <Events> EventsHistory = new ArrayList<>();
    private ArrayList <String> discountAreas = new ArrayList<String>();

      private Admin(String userName, String pohne, String password)
        {
            super(userName, pohne, password);
            discountAreas.add("cairo");
            discountAreas.add("elshobak");
        }
    
    public static Admin getInstance ()
    {
        if (admin == null)
             admin = new Admin ("admin", "0123456789","admin");
        return admin;
    }
    
    
    public void verify (driver d)
    {
        for (int i =0; i < dataBase.getInstance().pendingDrivers.size(); i++)
        {
              if (d.getLincese() != "")
              { 
                  dataBase.getInstance().drivers_List.add(d);
                  System.out.println("You have been added to our drivers list!");
                  dataBase.getInstance().pendingDrivers.remove(i);
              }
             else
                System.out.println("Failed! Wrong license");  
        }
     
    }
    
    public void suspend (User user)
    {
        if (user instanceof driver)
        {
            for (int i = 0; i < dataBase.getInstance().drivers_List.size(); i++)
                {
                    if (dataBase.getInstance().drivers_List.get(i).getUserName().equals(user.userName))
                    {   
                        dataBase.getInstance().drivers_List.remove(i);
                        dataBase.getInstance().SusspendedList.add(user);
                        System.out.println("Driver has been Suspended.");
                    }
                }
        }
        else if (user instanceof client)
        {
            for (int i = 0; i < dataBase.getInstance().clients_List.size(); i++)
                {
                    if (user.userName.equals(dataBase.getInstance().clients_List.get(i).getUserName()))
                    {   
                        dataBase.getInstance().clients_List.remove(i);
                        dataBase.getInstance().SusspendedList.add(user);
                        System.out.println("Client has been Suspended.");
                    }
                }            
        }
        else
            System.out.println("User not found");
    }
    
    
    public client validateDetilesClient(String userName, String pass)
    {
        
         for (int i = 0; i < dataBase.getInstance().clients_List.size(); i++)
         {
             if (userName.equals(dataBase.getInstance().clients_List.get(i).userName)) 
                {
                    if (pass.equals(dataBase.getInstance().clients_List.get(i).password))
                        return dataBase.getInstance().clients_List.get(i);
                    else
                    { 
                        System.out.println("wrong password");  
                        return null;
                    }
                }
         }
         System.out.println("User not found");  
         return null;
    }
    public driver validateDetilesDriver(String userName, String pass)
    {
         for (int i = 0; i < dataBase.getInstance().drivers_List.size(); i++)
            {
                if (userName.equals(dataBase.getInstance().drivers_List.get(i).userName)) 
                {
                    if (pass.equals(dataBase.getInstance().drivers_List.get(i).password))
                        return dataBase.getInstance().drivers_List.get(i);
                    else
                    { 
                        System.out.println("wrong password");  
                        return null;
                    }
                }
            }
         System.out.println("User not found");  
         return null;
    }
    public ArrayList<String> getAres()
    {
        return discountAreas;
    }
}
