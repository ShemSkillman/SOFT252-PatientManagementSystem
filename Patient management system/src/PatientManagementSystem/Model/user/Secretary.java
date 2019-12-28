/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.User;

/**
 *
 * @author Shem
 */
public class Secretary extends User {
    
    public Secretary(String name, String surname, String address)
    {
        super(name, surname, address, Role.Secretary);
    }
}
