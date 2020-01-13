/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Secretary;

import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Secretary.ViewDoctorAvailability;

/**
 *
 * @author Shem
 */
public class ControlDoctorAvailability {
    
    ModelMain modelMain;
    
    // Stores reference to window to hide/unhide
    private final ViewDoctorAvailability viewDoctorAvailability;  
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlDoctorAvailability(ModelMain modelMain){
        
        this.modelMain = modelMain;
        viewDoctorAvailability = new ViewDoctorAvailability();     
        
        viewDoctorAvailability.setVisible(true);
    }
    
    public void setVisible(boolean isVisible){
        
        viewDoctorAvailability.setVisible(isVisible);
    }
}
