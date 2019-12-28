/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.User;

/**
 *
 * @author Shemu
 */
public class Doctor extends User {
    
    public Doctor(String name, String surname, String address)
    {
        super(name, surname, address, Role.Doctor);
    }
}
