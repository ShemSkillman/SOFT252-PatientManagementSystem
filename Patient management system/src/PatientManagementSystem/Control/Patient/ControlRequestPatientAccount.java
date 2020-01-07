/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Patient;

import PatientManagementSystem.Control.Patient.RequestPatientAccount.ControlSendPatientRequestAction;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Patient.ViewRequestPatientAccount;

/**
 *
 * @author Shem
 */
public class ControlRequestPatientAccount {
    
    // Stores reference to window to hide/unhide
    private final ViewRequestPatientAccount viewRequestAccount;    
    
    private final ControlSendPatientRequestAction controlSendPatientRequestAction;
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlRequestPatientAccount(ModelMain modelMain){
        
        viewRequestAccount = new ViewRequestPatientAccount();        
        
        controlSendPatientRequestAction = new ControlSendPatientRequestAction(modelMain, viewRequestAccount);
        
        viewRequestAccount.setVisible(true);
    }
    
    public void setVisible(boolean isVisible){
        viewRequestAccount.setVisible(isVisible);
    }
}
