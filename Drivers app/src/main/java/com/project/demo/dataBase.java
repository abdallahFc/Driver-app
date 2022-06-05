package com.project.demo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
/**
 *
 * @author Ahmed Abdelnaser
 */
public class dataBase {

    private static dataBase MyDB = null;
    ArrayList<client> clients_List = new ArrayList<>();
    ArrayList<driver> drivers_List = new ArrayList<>();
    ArrayList<driver> pendingDrivers = new ArrayList<>();
    ArrayList<User> SusspendedList = new ArrayList<>();
    ArrayList<String> Holidays = new ArrayList<>();
    ArrayList<String> clientHistory = new ArrayList<>();

    private String currentDay = "sunday";

    public void setDay(String day) {
        currentDay = day;
    }

    public String getDay() {
        return currentDay;
    }

    private dataBase() {

        Holidays.add("Friday");
        Holidays.add("Monday");
    }

    public static dataBase getInstance() {
        if (MyDB == null) {
            MyDB = new dataBase();
        }
        return MyDB;
    }

}