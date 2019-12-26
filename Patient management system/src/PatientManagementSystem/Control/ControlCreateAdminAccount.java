/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control;

import PatientManagementSystem.Control.CreateAdminAccount.*;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Admin.ViewCreateAdminAccount;

/**
 *
 * @author Shem
 */

public class ControlCreateAdminAccount {
    
    // Stores reference to window to hide/unhide
    private final ViewCreateAdminAccount viewCreateAdminAccount;    
    
    // Control classes assoicated with this window
    private final ControlCreateAdminAccountAction controlRegisterAdminAction;
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlCreateAdminAccount(ModelMain modelMain){
        
        viewCreateAdminAccount = new ViewCreateAdminAccount();        
        controlRegisterAdminAction = new ControlCreateAdminAccountAction(modelMain, viewCreateAdminAccount);
        
        viewCreateAdminAccount.setVisible(true);
    }
    
    public void setVisible(boolean isVisible){
        viewCreateAdminAccount.setVisible(isVisible);
    }
    
}
