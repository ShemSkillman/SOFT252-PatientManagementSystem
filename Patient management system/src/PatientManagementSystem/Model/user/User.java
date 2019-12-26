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
    protected String name, surname, address, id;
    Role role;
    
    protected User(String name, String surname, String address, String id)
    {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.id = id;
        setRole();
    }
    
    private void setRole()
    {
        char letter = id.charAt(0);
        
        switch(letter)
        {
            case 'A':
                role = Role.Administrator;
                break;
            case 'S':
                role = Role.Secretary;
                break;
            case 'D':
                role = Role.Secretary;
                break;
            case 'P':
                role = Role.Patient;
                break;
        }
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

    public String getId() {
        return id;
    }       
    
    public Role getRole(){
        return role;
    }
}
