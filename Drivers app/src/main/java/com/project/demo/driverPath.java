package com.project.demo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class driverPath {
	driver activeDriver  = new driver ("", "", "" , "", "" );
	
	public void create(driver a1) {
		a1.Register();
		
	}
    public driver update(driver p) {
        for (driver person : dataBase.getInstance().drivers_List) {
        	return activeDriver=person.login(p.getUserName(),p.getPassword());
        }
		return null;
        
    }
    
    public driver find(String name) {
        for (driver person : dataBase.getInstance().drivers_List) {
            if (person.getUserName().equals(name)) {
                return person;
            }
        }

        return null;
    }
    public boolean update(String name , String pass) {
        
    	activeDriver=activeDriver.login(name,pass);
    	 
    	if (activeDriver==null)   {   
    		while (true)    
    		{   
    			String n, p, flag;   
    			System.out.println("Wanna continue? (y/n)");  
    			Scanner f  = new Scanner(System.in);    
    			flag = f.next();   
    			if (flag.equalsIgnoreCase("y")) 
    			{           
    			System.out.println("Enter the username: ");  
    			Scanner in  = new Scanner(System.in);  
    			n = in.next();   
    			System.out.println("Enter the password: ");    
    			Scanner sc = new Scanner(System.in); 
    			p = sc.next();              
    			update(n, p);   
  
    			}        else      
    				if (flag.equalsIgnoreCase("n"))   
    					return false;
    				else        
    					System.out.println("Wrong, please choose (y/n)");     
    			} 
    		}  
    	else   
    		return true;

    }
    
	@GetMapping(path="/printDrivers")
	@ResponseBody
	public ArrayList <driver> Get() {
	       return dataBase.getInstance().drivers_List;
		
	}
	
	@PostMapping("/createDriver")
	
	public driver createObj(@RequestBody driver a1) {
		
		create(a1);
		return a1;
		
	}

  
    @GetMapping("/findDriver/{name}")
    
    public driver retriveAllUsers(@PathVariable String name)  
    {  
    return find(name);  
    }

    @PutMapping("/loginDriver/{name}/{pass}")
    public String update1(@PathVariable String name,@PathVariable String pass) {
       
        if( update(name,pass)) {
        	return" welcome " + name;
        }
        else
        	return" invalid ";
        	
    }
    
    @PostMapping("/addFav/{name}")
	@ResponseBody
    public boolean favArea(@PathVariable String name)  
    {
    	return activeDriver.addFavArea(name,activeDriver.getUserName());
    }
    @GetMapping("/printFavArea")
	@ResponseBody
	public ArrayList <String> Getall() {
	       return activeDriver.Getlist(activeDriver.getUserName());
		
	}
    @PutMapping("/logoutDriver")
    public String logout() {
    	return"logout";
         }
    @GetMapping("/GetAllRate")
	@ResponseBody
    public HashMap<String,Integer> getRate()  
    {
   
    	return activeDriver.getRate();
    }
    @GetMapping("/GetAvgRate")
	@ResponseBody
    public float getAvgRate()  
    {
   
    	return activeDriver.getAvgRate();
    }
    
}
