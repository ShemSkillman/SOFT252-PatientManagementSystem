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
    
    private Account account;
    private ArrayList<PerformedAction> history = new ArrayList<PerformedAction>();

    public AccountHistory(Account account, PerformedAction peformedAction) {
        this.account = account;
        history.add(peformedAction);
    }
    
    public void addPerformedAction(String actionDescription) {
        
        PerformedAction action = new PerformedAction(actionDescription);
        history.add(action);
    }    

    public Account getAccount() {
        return account;
    }

    public ArrayList<PerformedAction> getHistory() {
        return history;
    }
    
    
}
