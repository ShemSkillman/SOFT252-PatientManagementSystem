/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.User.*;
import PatientManagementSystem.Model.ICommand;

import java.util.ArrayList;
/**
 *
 * @author Shem
 */
public class ModelAccountSystem {
    
    private final ArrayList<Account> accounts = new ArrayList<Account>();
    private Account loggedInAccount;
        
    private int accountNum = 0;
    private final int accountNumberOfDigits;
    
    public ModelAccountSystem() {
        accountNumberOfDigits = 4;
    }
    
    public boolean CreateAccount(User userToAdd, String password)
    {
        Role userPermissions;
        
        // Admin can create own account without having to be logged in another account
        // System will start with no accounts so this must be made possible
        if (loggedInAccount == null && 
                userToAdd.getRole() == Role.Administrator) 
        {
            userPermissions = Role.Administrator;
        }
        else if (loggedInAccount != null) // Otherwise use logged in account permissions
        {
            userPermissions = loggedInAccount.getUser().getRole();
        }
        else // Other accounts besides the admin account cannot be created without a logged in user
        {
            return false;
        }      
        
        // Only administrator can create accounts 
        // Includes exception of secretary creating patient accounts
        if (userPermissions == Role.Patient || userPermissions == Role.Doctor ||
                userPermissions == Role.Secretary && userToAdd.getRole() != Role.Patient)
            return false;
        
        String newId = generateUserId(userToAdd);
        Account newAccount = new Account(userToAdd, newId, password);        
        accounts.add(newAccount);
        
        System.out.println("ID: " + newAccount.getId() + " Password: " + newAccount.getPassword());
        
        // Account creation successful
        return true;
    }
    
    // Procudes unique ID for each created account 
    private String generateUserId(User user) {
        
        // Produces first letter of ID relating to the role of the user
        // eg. Administrator role gives first letter 'A'
        String role = user.getRole().toString();        
        char letter = role.charAt(0);        
        
        // Number part of ID is related to the number of accounts on the system
        // Account number incremented to so every ID is unique
        String numCode = Integer.toString(accountNum);
        accountNum++;
        
        // Works out how many zeros to add 
        // Account ID must have at least 4 digits
        int zeroFillCount = accountNumberOfDigits - numCode.length();
        
        String zeros = "";
        
        // Adds zeros to string 
        for (; zeroFillCount > 0; zeroFillCount--) {
            zeros += "0";
        }
        
        // Concatenates generated parts of id together
        String id = letter + zeros + numCode;
        
        return id;
    }
    
    public boolean RemoveAccount(String userId){
        
        Account accountToRemove = getAccount(userId);
        
        // Account cannot be removed if user is not registered
        if (accountToRemove == null) return false;        
        
        // Gets permissions based on the role of the logged in account
        Role userPermissions = loggedInAccount.getUser().getRole();
        
        // Only administrator can remove accounts 
        // Includes exception of secretary removing patient accounts
        if (userPermissions == Role.Patient || userPermissions == Role.Doctor ||
                userPermissions == Role.Secretary && accountToRemove.getUser().getRole() != Role.Patient)
            return false;      
        
        accounts.remove(accountToRemove);
        
        // Account removal successfull
        return true;
    }
    
    public boolean LogIn(String id, String password) {
        
        Account account = getAccount(id);
        
        // Account with entered user ID not found
        if (account == null) return false;
        
        // Account password not entered correctly
        if (!password.equals(account.getPassword())) return false;
        
        loggedInAccount = account;
        
        // Log in successfull
        return true;
    }
    
    public void LogOut() {
        
        loggedInAccount = null;
    }
    
    private Account getAccount(String userId)
    {
        for (Account account : accounts)
        {
            if (userId.equals(account.getId()))
                return account;
        }
        
        return null;
    }
    
    public ArrayList<Account> getAccountsOfTypeRole(Role role) {
        
        ArrayList<Account> accountsOfRole = new ArrayList<Account>();
        
        for (Account account : accounts)
        {
            if (role == account.getUser().getRole())
                accountsOfRole.add(account);
        }
        
        return accountsOfRole;
    }
    
    public Role getAccountRole(String userId){
        return getAccount(userId).getUser().getRole();
    }
}
