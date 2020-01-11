/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.AccountHistoryTracker;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class AccountHistory {
    
    private String accountId;
    private ArrayList<PerformedAction> history = new ArrayList<PerformedAction>();

    public AccountHistory(String accountId, PerformedAction peformedAction) {
        this.accountId = accountId;
        history.add(peformedAction);
    }
    
    public AccountHistory(String accountId, ArrayList<PerformedAction> history) {
        this.accountId = accountId;
        this.history = history;
    }
    
    public void addPerformedAction(String actionDescription) {
        
        PerformedAction action = new PerformedAction(actionDescription);
        history.add(action);
    }    

    public String getAccountId() {
        return accountId;
    }

    public ArrayList<PerformedAction> getHistory() {
        return history;
    }
    
    
}
