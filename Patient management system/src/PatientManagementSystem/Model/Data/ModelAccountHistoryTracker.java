/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;

import PatientManagementSystem.Model.Data.AccountHistoryTracker.AccountHistory;
import PatientManagementSystem.Model.Data.AccountHistoryTracker.PerformedAction;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.View.Event;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ModelAccountHistoryTracker {
    
    public Event onUpdateHistory = new Event();
    
    private ArrayList<AccountHistory> accountHistories = new ArrayList<AccountHistory>();
    
    private ModelAccountSystem modelAccountSystem;

    public ModelAccountHistoryTracker() {
    }    

    public void setModelAccountSystem(ModelAccountSystem modelAccountSystem) {
        this.modelAccountSystem = modelAccountSystem;
    }    
    
    
    public void recordAction(String actionDescription) {
        
        Account loggedInAccount = modelAccountSystem.getLoggedInAccount();       
        
        if (loggedInAccount == null) return;
        
        PerformedAction performedAction = new PerformedAction(actionDescription);
        
        AccountHistory accountHistory = lookForAccountHistory(loggedInAccount);
        
        if (accountHistory == null) 
        {
            accountHistory = new AccountHistory(loggedInAccount, performedAction);
            accountHistories.add(accountHistory);
        }
        else
        {
            accountHistory.addPerformedAction(actionDescription);
        }      
        
        onUpdateHistory.invoke();
    }
    
    public ArrayList<PerformedAction> getAccountHistory(Account account) {
        
        AccountHistory accountHistory = lookForAccountHistory(account);
        
        return accountHistory.getHistory();
    }
    
    private AccountHistory lookForAccountHistory(Account account){
        
        for(AccountHistory history : accountHistories)
        {
            if (history.getAccount() == account){
                return history;
            }
        }
        
        return null;
    }
}
