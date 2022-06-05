
package com.project.demo;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class clientPath {
	 client activeClient  = new client ("","","");
	
	public void create(client a1) {
		a1.Register();
	}
    
    public client find(String name) {
        for (client person : dataBase.getInstance().clients_List) {
            if (person.getUserName().equals(name)) {
                return person;
            }
        }

        return null;
    }
   
    public boolean update(String name , String pass) {
     
    	activeClient=activeClient.login(name,pass);
 
    	if (activeClient==null)   {   
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
   
   
	@GetMapping(path="/PrintClients")
	@ResponseBody
	public ArrayList <client> Get() {
		
	       return dataBase.getInstance().clients_List;
		
	}
	
	@PostMapping(path="/createClient")
	
	public client createObj(@RequestBody client a1) {
		
		create(a1);
		return a1;
		
	}
	@GetMapping(path="/offers")
	@ResponseBody
	public ArrayList<Offer> Offers(){
		return activeClient.getOffers();
	}
	
	
  
    @GetMapping("/findClient/{name}")
    
    public client retriveAllUsers(@PathVariable String name)  
    {  
    return find(name);  
    }

    @PutMapping("/loginClient/{name}/{pass}")
    public String update1(@PathVariable String name,@PathVariable String pass) {
       
        if( update(name,pass)) {
        return" welcome " + name;
        }
        else
		  return "invalid";
        	
    }
    
	@PostMapping(path="/Request/{name}/{name1}")
	
	public void createObj(@PathVariable String name,@PathVariable String name1) {
	    activeClient.request(name, name1);
	}
    @PutMapping("/logoutClient")
    public String logout() {
    	return"logout";
    }
    
    @PostMapping(path="/chooseDriver")
    public String choose() {
    	return activeClient.chooseOffer();
    }
	@PostMapping(path="/Rate/{rate}/{name1}")
	public void Rate(@PathVariable int rate,@PathVariable String name1) {
	    activeClient.giveRate(rate, name1);
	}   
}