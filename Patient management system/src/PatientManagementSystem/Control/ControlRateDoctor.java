/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control;

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
    
    private final ViewRateDoctor viewRateDoctor;
    private final ModelMain modelMain;
    
    ArrayList<Account> allDoctorAccounts;

    public ControlRateDoctor(ModelMain modelMain) {
        
        viewRateDoctor = new ViewRateDoctor();
        
        this.modelMain = modelMain;     
        
        viewRateDoctor.setVisible(true);
    }
    
    public void setVisible(boolean isVisible){
        viewRateDoctor.setVisible(isVisible);
    }
    
    
    
}
