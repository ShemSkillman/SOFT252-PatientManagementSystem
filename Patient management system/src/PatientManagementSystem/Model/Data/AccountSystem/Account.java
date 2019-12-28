/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.AccountSystem;
import PatientManagementSystem.Model.User.User;

/**
 *
 * @author Shem
 */
public class Account {
    
    private User user;
    private final String id;
    private String password;

    public Account(User user, String id, String password)
    {
        this.user = user;
        this.id = id;
        this.password = password;
    }

    public User getUser() {
        return user;
    }
    
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }        
}
