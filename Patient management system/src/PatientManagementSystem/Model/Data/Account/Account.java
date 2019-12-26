/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.Account;
import PatientManagementSystem.Model.User.User;

/**
 *
 * @author Shem
 */
public class Account {
    
    private User user;
    private String password;

    public Account(User user, String password)
    {
        this.user = user;
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }        
}
