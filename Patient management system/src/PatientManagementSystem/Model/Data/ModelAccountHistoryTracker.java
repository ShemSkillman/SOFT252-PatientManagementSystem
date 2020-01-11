/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;

import PatientManagementSystem.Model.Data.AccountHistoryTracker.AccountHistory;
import PatientManagementSystem.Model.Data.AccountHistoryTracker.PerformedAction;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Event;
import PatientManagementSystem.IObserverType;
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
public class ModelAccountHistoryTracker implements IObserverType<String>{
    
    public Event onUpdateHistory = new Event();
    private ModelAccountSystem modelAccountSystem;
    
    File file = new File("AccountHistoryTrackerData.txt");
    
    // data to save
    private ArrayList<AccountHistory> accountHistories = new ArrayList<AccountHistory>();
    

    public ModelAccountHistoryTracker() {
        
    }    
    
    private void saveData() {
        
        JSONObject root = new JSONObject();
        
        JSONArray historiesArray = new JSONArray();
        
        for (int i = 0; i < accountHistories.size(); i++)
        {
            AccountHistory history = accountHistories.get(i);
            
            String id = history.getAccountId();            
            JSONArray performedActionsArray = new JSONArray();
            
            JSONObject historyObject = new JSONObject();
            
            ArrayList<PerformedAction> actions = history.getHistory();
            
            for (int j = 0; j < actions.size(); j++)
            {
                PerformedAction action = actions.get(j);
                
                JSONObject actionObject = new JSONObject();
                
                actionObject.put("actionDescription", action.getActionDescription());
                actionObject.put("dateAndTime", action.getDateAndTime());
                
                performedActionsArray.add(actionObject);
            }
            
            historyObject.put("id", id);
            historyObject.put("performedActions", performedActionsArray);
            
            historiesArray.add(historyObject);
        }
        
        root.put("histories", historiesArray);
        
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(root.toJSONString());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }
    
    private void loadData() {
        StringBuilder jsonIn = new StringBuilder();
        
        try (Scanner reader = new Scanner(file)) {
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
            
            JSONArray objHistories = (JSONArray)objRoot.get("histories");
            
            ArrayList<AccountHistory> histories = new ArrayList();
            
            for (int i = 0; i < objHistories.size(); i++) 
            {
                JSONObject objHistory = (JSONObject)objHistories.get(i);
                
                String accountId = (String)objHistory.get("id");
                
                JSONArray objActions = (JSONArray)objHistory.get("performedActions");
                
                ArrayList<PerformedAction> actions = new ArrayList();
                
                for (int j = 0; j < objActions.size(); j++) 
                {
                    JSONObject objAction = (JSONObject)objActions.get(j);
                    
                    String dateAndTime = (String)objAction.get("dateAndTime");
                    String actionDescription = (String)objAction.get("actionDescription");
                    
                    PerformedAction action = new PerformedAction(actionDescription, dateAndTime);
                    
                    actions.add(action);
                }
                
                AccountHistory history = new AccountHistory(accountId, actions);
                
                histories.add(history);
            }
            
            accountHistories = histories;
        }
        catch (ParseException ex) {
            System.out.println(ex.toString());
        }
    }   

    public void setModelAccountSystem(ModelAccountSystem modelAccountSystem) {
        this.modelAccountSystem = modelAccountSystem;
        modelAccountSystem.onRemoveAccount.addObserver(this);
        loadData();
    }    
    
    
    public void recordAction(String actionDescription) {
        
        Account loggedInAccount = modelAccountSystem.getLoggedInAccount();       
        
        if (loggedInAccount == null) return;
        
        PerformedAction performedAction = new PerformedAction(actionDescription);
        
        AccountHistory accountHistory = lookForAccountHistory(loggedInAccount);
        
        if (accountHistory == null) 
        {
            accountHistory = new AccountHistory(loggedInAccount.getId(), performedAction);
            accountHistories.add(accountHistory);
        }
        else
        {
            accountHistory.addPerformedAction(actionDescription);
        }      
        
        onUpdateHistory.invoke();
        
        saveData();
    }
    
    public ArrayList<PerformedAction> getAccountHistory(Account account) {
        
        AccountHistory accountHistory = lookForAccountHistory(account);
        
        return accountHistory.getHistory();
    }
    
    private AccountHistory lookForAccountHistory(Account account){
        
        for(AccountHistory history : accountHistories)
        {
            if (history.getAccountId().compareTo(account.getId()) == 0){
                return history;
            }
        }
        
        return null;
    }
    
    public void removeHistoryForAccount(String accountId)
    {
        for (int i = 0; i < accountHistories.size(); i++)
        {
            AccountHistory history = accountHistories.get(i);
            
            if (history.getAccountId().compareTo(accountId) == 0)
            {
                accountHistories.remove(i);
                return;
            }
        }            
    }
    
    @Override
    public void update(String id) 
    {
        removeHistoryForAccount(id);
    }
}
