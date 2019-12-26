/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control;
import PatientManagementSystem.Control.AccountLogIn.ControlRegisterAdminAction;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.ViewLogIn;

/**
 *
 * @author Shem
 */
public class ControlAccountLogIn {
        
    private final ViewLogIn viewLogin;    
    
    private final ControlRegisterAdminAction controlCreateAdminAction;
    
    public ControlAccountLogIn(ModelMain modelMain){
        
        viewLogin = new ViewLogIn();        
        controlCreateAdminAction = new ControlRegisterAdminAction(modelMain, viewLogin);
        
        viewLogin.setVisible(true);
    }
    
    public void setVisible(boolean isVisible){
        viewLogin.setVisible(isVisible);
    }
    
}
