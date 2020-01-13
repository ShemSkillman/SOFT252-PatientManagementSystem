/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Secretary;

import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Secretary.ViewRemovePatient;

/**
 *
 * @author Shem
 */
public class ControlRemovePatient {
    
    ModelMain modelMain;
    
    // Stores reference to window to hide/unhide
    private final ViewRemovePatient viewRemovePatient;  
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlRemovePatient(ModelMain modelMain){
        
        this.modelMain = modelMain;
        viewRemovePatient = new ViewRemovePatient();     
        
        viewRemovePatient.setVisible(true);
    }
    
    public void setVisible(boolean isVisible){
        
        viewRemovePatient.setVisible(isVisible);
    }
}
