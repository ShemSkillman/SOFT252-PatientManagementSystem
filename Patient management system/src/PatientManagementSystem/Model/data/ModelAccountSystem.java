/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.User.*;
import PatientManagementSystem.View.EventSystem.Event;
import PatientManagementSystem.View.EventSystem.EventType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Shem
 */
public class ModelAccountSystem {
    
    public Event onUpdateAccounts = new Event();
    public EventType<String> onRemoveAccount = new EventType<>();
    
    File file = new File("AccountSystemData.txt");
    
    // Data that needs to be saved
    private final ArrayList<Account> accounts = new ArrayList<Account>();
    private int accountNumber = 0;
    
    private Account loggedInAccount;        
    private final int accountNumberOfDigits = 4;
    private final ModelAccountHistoryTracker modelAccountHistoryTracker;
    
    public ModelAccountSystem(ModelAccountHistoryTracker modelAccountHistoryTracker) {
        
        this.modelAccountHistoryTracker = modelAccountHistoryTracker;
        
        loadData();
    }  
    
    private void saveData() {
        JSONObject root = new JSONObject();
        
        root.put("accountNumber", Integer.toString(accountNumber));
        
        JSONArray accountArray = new JSONArray();
        
        for (int i = 0; i < accounts.size(); i++)
        {
            JSONObject userObject = new JSONObject();
            
            Account account = accounts.get(i);
            User user = account.getUser();
            
            userObject.put("firstName", user.getName());
            userObject.put("surname", user.getSurname());
            userObject.put("address", user.getAddress());
            userObject.put("role", user.getRole().toString());
            
            if (user instanceof Patient) {                
                Patient patient = (Patient)user;                
                userObject.put("gender", patient.getGender().toString());
                userObject.put("age", Integer.toString(patient.getAge()));
            }
            
            JSONObject accountObject = new JSONObject();
            
            accountObject.put("user", userObject);
            accountObject.put("id", account.getId());
            accountObject.put("password", account.getPassword());          
            
            accountArray.add(accountObject);
        }
        
        root.put("accounts", accountArray);
        
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(root.toJSONString());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }       
    }
    
    private void loadData(){
        
        StringBuilder jsonIn = new StringBuilder();
        
        try (java.util.Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine())
            {
                jsonIn.append(reader.nextLine());
            }
        }            
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
        
        try {              
        JSONParser parser = new JSONParser();        
        JSONObject objRoot = (JSONObject)parser.parse(jsonIn.toString());
        
        accountNumber = Integer.parseInt((String)objRoot.get("accountNumber"));
        
        JSONArray arrayAccounts = (JSONArray)objRoot.get("accounts");
        
        for (int i = 0; i < arrayAccounts.size(); i++) 
        {
            JSONObject objAccount = (JSONObject)arrayAccounts.get(i);
            
            String id = (String)objAccount.get("id");
            String password = (String)objAccount.get("password");            
            
            JSONObject objUser = (JSONObject)objAccount.get("user");
            
            String firstName = (String)objUser.get("firstName");
            String surname = (String)objUser.get("surname");
            String address = (String)objUser.get("address");
            Role role = Role.valueOf((String)objUser.get("role"));
            
            User user = null;
            
            switch(role)
            {
                case Administrator:
                    user = new Administrator(firstName, surname, address);
                    break;
                case Secretary:
                    user = new Secretary(firstName, surname, address);
                    break;
                case Doctor:
                    user = new Doctor(firstName, surname, address);
                    break;
                case Patient:
                    Gender gender = Gender.valueOf((String)objUser.get("gender"));
                    int age = Integer.parseInt((String)objUser.get("age"));
                    user = new Patient(firstName, surname, address, age, gender);   
                    break;
            }
            
            Account account = new Account(user, id, password);
            
            accounts.add(account);
            
        }
        
        } catch(ParseException ex) {
            System.out.println(ex.toString());
        }
        
    }
    
    public ArrayList<Account> getAccountsOfTypeRole(Role role) {
        
        ArrayList<Account> accountsOfRole = new ArrayList<>();
        
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
        
        saveData();
        
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
        String numCode = Integer.toString(accountNumber);
        accountNumber++;
        
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
        
        modelAccountHistoryTracker.removeHistoryForAccount(accountToRemove.getId());
        
        
        modelAccountHistoryTracker.recordAction("Removed " + accountToRemove.getUser().getRole().toString()
                + " account with ID " + accountToRemove.getId());
        
        onUpdateAccounts.invoke();
        onRemoveAccount.invoke(accountToRemove.getId());
        
        saveData();
        
        // Account removal successfull
    }
    
    public boolean logIn(String id, String password) {
        
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
    
    public void logOut() {
        
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
                
        ArrayList<String> names = new ArrayList<>();
        
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
