/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;
import PatientManagementSystem.Model.User.*;
import PatientManagementSystem.Model.Data.Account.*;

import java.util.ArrayList;
/**
 *
 * @author Shem
 */
public class ModelAccountSystem {
    
    ArrayList<Account> accounts = new ArrayList<Account>();

    public ModelAccountSystem() {
    }
    
    public boolean CreateAccount(User requestingUser, User userToAdd, String password)
    {
        Role userPermissions = requestingUser.getRole();
        
        // Only administrator can create accounts 
        // Includes exception of secretary creating patient accounts
        if (userPermissions == Role.Patient || userPermissions == Role.Doctor ||
                userPermissions == Role.Secretary && userToAdd.getRole() != Role.Patient)
            return false;
            
        
        // Prevents a user from being registered more than once
        if (getUserAccount(userToAdd) != null) return false;
        
        Account newAccount = new Account(userToAdd, password);        
        accounts.add(newAccount);
        
        // Account creation successfull
        return true;
    }
    
    public boolean RemoveAccount(User requestingUser, User userToRemove)
    {
        Role userPermissions = requestingUser.getRole();
        
        // Only administrator can remove accounts 
        // Includes exception of secretary removing patient accounts
        if (userPermissions == Role.Patient || userPermissions == Role.Doctor ||
                userPermissions == Role.Secretary && userToRemove.getRole() != Role.Patient)
            return false;
        
        Account userAccount = getUserAccount(userToRemove);
        
        // Account cannot be removed if user is not registered
        if (userAccount == null) return false;        
        
        accounts.remove(userAccount);
        
        // Account removal successfull
        return true;
    }
    
    private Account getUserAccount(User user)
    {
        for (Account account : accounts)
        {
            if (account.getUser() == user)
                return account;
        }
        
        return null;
    }
}
