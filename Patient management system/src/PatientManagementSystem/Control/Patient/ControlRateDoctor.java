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
    
    private final ModelMain modelMain;
    
    // Stores reference to window to hide/unhide
    private final ViewRateDoctor viewRateDoctor; 
    
    private final ControlRateDoctorAction controlRateDoctorAction;
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlRateDoctor(ModelMain modelMain){
        this.modelMain = modelMain;
        viewRateDoctor = new ViewRateDoctor();   
        controlRateDoctorAction = new ControlRateDoctorAction(viewRateDoctor, modelMain);     
        
        refresh();        
        
        viewRateDoctor.setVisible(true);
    }
    
    private void refresh() {
        ArrayList<Account> doctorAccounts = modelMain.getModelAccountSystem().getAccountsOfTypeRole(Role.Doctor);
        ArrayList<String> doctorNames = modelMain.getModelAccountSystem().getAccountNames(doctorAccounts);
        viewRateDoctor.setDoctors(doctorNames);
    }
    
    public void setVisible(boolean isVisible){
        if (isVisible) refresh();
        
        viewRateDoctor.setVisible(isVisible);
    }
}
