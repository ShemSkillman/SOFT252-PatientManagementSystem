/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Admin;

import PatientManagementSystem.Control.Admin.RemoveAccount.ControlRemoveAccountAction;
import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.User.Role;
import PatientManagementSystem.View.Admin.ViewRemoveAccount;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlRemoveAccount implements IObserver {
    
    private final ModelMain modelMain;
    
    // Stores reference to window to hide/unhide
    private final ViewRemoveAccount viewRemoveAccount;  
    
    private final ControlRemoveAccountAction controlRemoveAccountAction;
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlRemoveAccount(ModelMain modelMain){
        
        this.modelMain = modelMain;
        
        modelMain.getModelAccountSystem().onUpdateAccounts.addObserver(this);
        
        viewRemoveAccount = new ViewRemoveAccount();   
        viewRemoveAccount.onChooseNewAccountType.addObserver(this);
        controlRemoveAccountAction = new ControlRemoveAccountAction(modelMain, viewRemoveAccount);        
        
        updateList();
        
        viewRemoveAccount.setVisible(true);
    }
    
    @Override
    public void update() {
        updateList();
    }
    
    private void updateList() {
                
        String accountType = viewRemoveAccount.getAccountType();
        
        ArrayList<Account> accountsOfType = new ArrayList<Account>();
        
        if (accountType.compareTo("Secretary") == 0)
        {
            accountsOfType = modelMain.getModelAccountSystem().getAccountsOfTypeRole(Role.Secretary);
        }
        else if (accountType.compareTo("Doctor") == 0)
        {
            accountsOfType = modelMain.getModelAccountSystem().getAccountsOfTypeRole(Role.Doctor);
        }        
        
        ArrayList<String> names = modelMain.getModelAccountSystem().getAccountNames(accountsOfType);
        
        viewRemoveAccount.setListOfNames(names);
        
        if (accountsOfType.size() < 1)
        {
            viewRemoveAccount.enableRemoveAccount(false);
            return;
        }
        
        viewRemoveAccount.enableRemoveAccount(true);
    }
    
    public void setVisible(boolean isVisible){
        
        if (isVisible) updateList();
        
        viewRemoveAccount.setVisible(isVisible);
    }
}
