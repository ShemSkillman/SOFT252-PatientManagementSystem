/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Admin;

import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Admin.ViewAdminDoctorRatings;

/**
 *
 * @author Shem
 */
public class ControlAdminDoctorRatings {
    
    // Stores reference to window to hide/unhide
    private final ViewAdminDoctorRatings viewAdminDoctorRatings;  
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlAdminDoctorRatings(ModelMain modelMain){
        
        viewAdminDoctorRatings = new ViewAdminDoctorRatings();  
        
        viewAdminDoctorRatings.setVisible(true);
    }
    
    public void setVisible(boolean isVisible){
        viewAdminDoctorRatings.setVisible(isVisible);
    }
}
