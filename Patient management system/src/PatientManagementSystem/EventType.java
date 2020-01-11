/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem;

import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class EventType<T>{
    
    ArrayList<IObserverType<T>> observers = new ArrayList();
        
    public void addObserver(IObserverType<T> observer){
        
        if (observers.contains(observer)) return;
        
        observers.add(observer);
    }
    
    public void removeObserver(IObserverType<T> observer){
        
        if (!observers.contains(observer)) return;
        
        observers.remove(observer);
    }
    
    public void invoke(T data){
         
         for(var observer : observers)
         {
             observer.update(data);
         }
     }
}
