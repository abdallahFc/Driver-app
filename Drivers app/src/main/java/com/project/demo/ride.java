package com.project.demo;

import java.util.ArrayList;

public class ride {

	  private String source;
	   private String destination;
	   dataBase MyDB;
	   Offer offer;

	   public ride(String source, String destination, client c, ArrayList<Offer> offersList) {
	        this.source = source;

	     notifyDriver(c, destination, offersList);

	    }

	    public void setOffer(Offer o)
	    {
	        offer = o;
	    }
	    public Offer getOffer()
	    {
	        return offer;
	    }
	    
	    public String getSource() {
	        return source;
	    }

	    public String getDestination() {
	        return destination;
	    }

	    public void setSource(String source) {
	        this.source = source;
	    }

	    public void setDestination(String destination) {
	        this.destination = destination;
	    }
	  
	    public void notifyDriver(client c, String destination, ArrayList<Offer> offersList)
	    {
	        for (int i=0 ; i< dataBase.getInstance().drivers_List.size() ;i++) 
	        {
	            for (int j =0; j < dataBase.getInstance().drivers_List.get(i).favArea_List.size(); j++)
	            {
	                if (dataBase.getInstance().drivers_List.get(i).favArea_List.get(j).equals(this.source))
	                {
	               
	                   offersList.add(dataBase.getInstance().drivers_List.get(i).update(c, destination));
	                 }
	            }

	        }
	        }
	   }
