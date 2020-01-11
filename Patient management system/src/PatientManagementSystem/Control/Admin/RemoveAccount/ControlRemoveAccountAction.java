/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Admin.RemoveAccount;

import PatientManagementSystem.IObserver;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Admin.ViewRemoveAccount;

/**
 *
 * @author Shem
 */
public class ControlRemoveAccountAction implements IObserver {
    
    private ModelMain modelMain;
    private ViewRemoveAccount viewRemoveAccount;
    
    public ControlRemoveAccountAction(ModelMain modelMain, ViewRemoveAccount viewRemoveAccount) {
        this.modelMain = modelMain;
        this.viewRemoveAccount = viewRemoveAccount;
        
        viewRemoveAccount.onRemoveAccount.addObserver(this);
    }    
    
    // Gets inputs from text components on the form
    // Sends details to model to create the accout
    @Override
    public void update() {
        
        String Id = viewRemoveAccount.getSelectedAccountId();
        
        Account account = modelMain.getModelAccountSystem().getAccount(Id);
        
        modelMain.getModelAccountSystem().RemoveAccount(account);
        
        viewRemoveAccount.showMessage("Account removed succesfully", "The " + account.getUser().getRole().toString() + " account with"
                + " ID " + account.getId() + " has been removed from the system");
        
    }
}
