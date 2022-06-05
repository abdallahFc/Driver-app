package com.project.demo;

/**
 *
 * @author Abdallah
 */
public class Rate {
    
    float avgRate = 0;

    Rate(int rate, String driverName, client c) {
        boolean x = true;
        for (int i = 0; i < dataBase.getInstance().drivers_List.size(); i++) {
            if (dataBase.getInstance().drivers_List.get(i).getUserName().equalsIgnoreCase(driverName)) {
                dataBase.getInstance().drivers_List.get(i).rating_List.put(c.userName, rate);
                CalculateAvgRate(dataBase.getInstance().drivers_List.get(i), rate);
                x = false;
                System.out.println("rating");
            }
        }
        if (x) {
            System.out.println("Not Found!!");
        }
    }

    void CalculateAvgRate(driver d, int rate) {
        float sumRate = 0;
        for (int i : d.rating_List.values()) {
            sumRate = sumRate + i;
        }
        avgRate = sumRate / d.rating_List.size();
        d.setAvg(avgRate);
    }
}