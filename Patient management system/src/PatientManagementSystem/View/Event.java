/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.View;

import PatientManagementSystem.Control.IObserver;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class Event{
    
    ArrayList<IObserver> observers = new ArrayList<IObserver>();
    
    public Event(){}
    
    public void addObserver(IObserver observer){
        
        if (observers.contains(observer)) return;
        
        observers.add(observer);
    }
    
    public void removeObserver(IObserver observer){
        
        if (!observers.contains(observer)) return;
        
        observers.remove(observer);
    }
    
    public void invoke(){
         
         for(IObserver observer : observers)
         {
             observer.update();
         }
     }
}
