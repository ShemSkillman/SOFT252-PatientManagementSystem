/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Admin;

import PatientManagementSystem.Control.Admin.AddAccount.ControlAddAccountAction;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Admin.ViewAddAccount;

/**
 *
 * @author Shem
 */
public class ControlAddAccount {
    
    // Stores reference to window to hide/unhide
    private final ViewAddAccount viewAddAccount;    
    
    private final ControlAddAccountAction controlAddAccountAction;
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlAddAccount(ModelMain modelMain){
        
        viewAddAccount = new ViewAddAccount();   
        controlAddAccountAction = new ControlAddAccountAction(modelMain, viewAddAccount);
        
        viewAddAccount.setVisible(true);
    }
    
    public void setVisible(boolean isVisible){
        viewAddAccount.setVisible(isVisible);
    }
}
