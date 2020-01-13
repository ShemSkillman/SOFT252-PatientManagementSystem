/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.PrescriptionSystem;

/**
 *
 * @author Shem
 */
public class Medicine {
    
    private final String name;
    private int quantity = 0;

    public Medicine(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }  

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void consume(int amount) {
        quantity -= amount;
    }
    
    public void add(int amount) {
        quantity += amount;
    }
}
