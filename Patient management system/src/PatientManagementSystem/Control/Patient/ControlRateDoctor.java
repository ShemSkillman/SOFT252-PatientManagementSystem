/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Patient;

import PatientManagementSystem.Control.Patient.RateDoctor.ControlRateDoctorAction;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.User.Role;
import PatientManagementSystem.View.Patient.ViewRateDoctor;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlRateDoctor {
    
    // Stores reference to window to hide/unhide
    private final ViewRateDoctor viewRateDoctor; 
    
    private final ControlRateDoctorAction controlRateDoctorAction;
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlRateDoctor(ModelMain modelMain){
        
        viewRateDoctor = new ViewRateDoctor();        
        
        ArrayList<Account> doctorAccounts = modelMain.getModelAccountSystem().getAccountsOfTypeRole(Role.Doctor);
        ArrayList<String> doctorNames = modelMain.getModelAccountSystem().getAccountNames(doctorAccounts);
        viewRateDoctor.setDoctors(doctorNames);
        
        controlRateDoctorAction = new ControlRateDoctorAction(viewRateDoctor, modelMain);
        
        viewRateDoctor.setVisible(true);
    }
    
    public void setVisible(boolean isVisible){
        viewRateDoctor.setVisible(isVisible);
    }
}
