/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Shared;
import PatientManagementSystem.Control.Shared.AccountLogIn.ControlLogInAction;
import PatientManagementSystem.Control.Shared.AccountLogIn.ControlRegisterAdminAction;
import PatientManagementSystem.Control.Shared.AccountLogIn.ControlRequestPatientAccountAction;
import PatientManagementSystem.Model.*;
import PatientManagementSystem.View.*;

/**
 *
 * @author Shem
 */
public class ControlAccountLogIn {  
    
    private final ViewLogIn viewLogIn;
    private final ControlRegisterAdminAction controlRegisterAdminAction;
    private final ControlLogInAction controlLogInAction;
    private final ControlRequestPatientAccountAction controlRequestPatientAccountAction;
    
    public ControlAccountLogIn(ModelMain modelMain){        
        
        viewLogIn = new ViewLogIn();
        
        controlRegisterAdminAction = new ControlRegisterAdminAction(modelMain, viewLogIn);
        controlLogInAction = new ControlLogInAction(modelMain, viewLogIn);
        controlRequestPatientAccountAction = new ControlRequestPatientAccountAction(modelMain, viewLogIn);
        
        viewLogIn.setVisible(true);
    }
    
    public void setVisible(boolean isVisible){
        viewLogIn.setVisible(isVisible);
    }
    
}
