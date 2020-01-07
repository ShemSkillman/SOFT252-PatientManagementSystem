/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Patient;

import PatientManagementSystem.Control.Patient.RequestAccountDeletion.ControlRequestAccountDeletionAction;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Patient.ViewRequestAccountDeletion;

/**
 *
 * @author Shem
 */
public class ControlRequestAccountDeletion {
    
    // Stores reference to window to hide/unhide
    private final ViewRequestAccountDeletion viewRequestAccountDeletion;    
    
    private final ControlRequestAccountDeletionAction controlRequestAccountDeletionAction;
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlRequestAccountDeletion(ModelMain modelMain){
        
        viewRequestAccountDeletion = new ViewRequestAccountDeletion();   
        controlRequestAccountDeletionAction = new ControlRequestAccountDeletionAction(viewRequestAccountDeletion, modelMain);
        
        viewRequestAccountDeletion.setVisible(true);
    }
    
    public void setVisible(boolean isVisible){
        viewRequestAccountDeletion.setVisible(isVisible);
    }
}
