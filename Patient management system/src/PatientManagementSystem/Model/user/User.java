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
public abstract class User {
    protected String name, surname, address;
    protected final Role role;
    
    protected User(String name, String surname, String address, Role role)
    {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }   
    
    public Role getRole(){
        return role;
    }
}
