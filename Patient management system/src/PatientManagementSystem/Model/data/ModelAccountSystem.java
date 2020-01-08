/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.User.*;
import PatientManagementSystem.View.Event;

import java.util.ArrayList;
/**
 *
 * @author Shem
 */
public class ModelAccountSystem {
    
    public Event onRemoveAccount = new Event();
    
    private final ArrayList<Account> accounts = new ArrayList<Account>();
    private Account loggedInAccount;
        
    private int accountNum = 3;
    private final int accountNumberOfDigits;
    private final ModelAccountHistoryTracker modelAccountHistoryTracker;
    
    public ModelAccountSystem(ModelAccountHistoryTracker modelAccountHistoryTracker) {
        
        this.modelAccountHistoryTracker = modelAccountHistoryTracker;
        
        accountNumberOfDigits = 4;
        
        addDefaultAccountsToSystem();
    }
    
    // Populates system with accounts on start-up
    private void addDefaultAccountsToSystem()
    {
        Patient patient = new Patient("Shem", "Skillman", "14 Oakwood Drive, Modbury", 20, Gender.Male);
        accounts.add(new Account(patient, "P0001", "1"));
        
        Doctor doctor = new Doctor("Mary", "Curie", "4 Applewood Drive, Paignton");
        accounts.add(new Account(doctor, "D0002", "1"));
        
        Administrator admin = new Administrator("Adam", "Shandler", "5 Smokewood place, Bombury");
        accounts.add(new Account(admin, "A0000", "1"));
        
        Secretary secretary = new Secretary("Margret", "Flinders", "3 Faraday road, Kingstown");
        accounts.add(new Account(secretary, "S0003", "1"));
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
    
    public Account CreateAccount(User userToAdd, String password)
    {        
        String newId = generateUserId(userToAdd);
        Account newAccount = new Account(userToAdd, newId, password);        
        accounts.add(newAccount);
        
        System.out.println("ID: " + newAccount.getId() + " Password: " + newAccount.getPassword());
        
        modelAccountHistoryTracker.recordAction("Created new " + userToAdd.getRole().toString() + " account with ID " + newId);
        
        // Account creation successful
        return newAccount;
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
    
    public void RemoveAccount(Account accountToRemove){
        
        // Account cannot be removed if user is not registered
        if (accountToRemove == null) return;    
        
        accounts.remove(accountToRemove);
        
        modelAccountHistoryTracker.recordAction("Removed " + accountToRemove.getUser().getRole().toString()
                + " account with ID " + accountToRemove.getId());
        
        onRemoveAccount.invoke();
        
        // Account removal successfull
    }
    
    public boolean LogIn(String id, String password) {
        
        Account account = getAccount(id);
        
        // Account with entered user ID not found
        if (account == null) return false;
        
        // Account password not entered correctly
        if (!password.equals(account.getPassword())) return false;
        
        loggedInAccount = account;
        
        modelAccountHistoryTracker.recordAction("Logged in");
        
        // Log in successfull
        return true;
    }
    
    public void LogOut() {
        
        modelAccountHistoryTracker.recordAction("Logged out");
        loggedInAccount = null;        
    }
    
    public Account getAccount(String userId)
    {
        for (Account account : accounts)
        {
            if (userId.equals(account.getId()))
                return account;
        }
        
        return null;
    }
    
    public ArrayList<String> getAccountNames(ArrayList<Account> accounts) {
                
        ArrayList<String> names = new ArrayList<String>();
        
        for(Account account : accounts) 
        {
            String name = account.getId() + " " + account.getUser().getName() + 
                    " " + account.getUser().getSurname();
            names.add(name);
        }
        
        return names;
    }
    
    public Account getLoggedInAccount() {
        return loggedInAccount;
    }
    
    public Role getAccountRole(String userId){
        return getAccount(userId).getUser().getRole();
    }
}
