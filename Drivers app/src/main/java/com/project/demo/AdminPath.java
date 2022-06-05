package com.project.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class AdminPath {
    public client deleteClient(String name) {
        for (client person : dataBase.getInstance().clients_List) {
            if (person.getUserName().equals(name)) {
                dataBase.getInstance().clients_List.remove(person);
                dataBase.getInstance().SusspendedList.add(person);
                return person;
            }
        }

        return null;
    }
    
    public driver deleteDriver(String name) {
        for (driver person : dataBase.getInstance().drivers_List) {
            if (person.getUserName().equals(name)) {
                dataBase.getInstance().drivers_List.remove(person);
                dataBase.getInstance().SusspendedList.add(person);
                return person;
            }
        }

        return null;
    }
    @DeleteMapping("/suspendClient/{name1}")
    
    public client deleteObj(@PathVariable String name1)  
    {  
    return deleteClient(name1);  
    }
    @DeleteMapping("/suspendDriver/{name2}")
    
    public driver deleteOb(@PathVariable String name2)  
    {  
    return deleteDriver(name2);  
    }
    

}
