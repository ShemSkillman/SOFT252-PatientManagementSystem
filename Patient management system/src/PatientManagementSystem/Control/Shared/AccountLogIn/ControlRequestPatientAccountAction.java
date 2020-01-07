/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Shared.AccountLogIn;
import PatientManagementSystem.Model.*;
import PatientManagementSystem.Control.IObserver;
import PatientManagementSystem.View.ViewLogIn;

/**
 *
 * @author Shem
 */
public class ControlRequestPatientAccountAction implements IObserver {
    
    private final ModelMain modelMain;
    
    public ControlRequestPatientAccountAction(ModelMain modelMain, ViewLogIn viewLogin){
        this.modelMain = modelMain;
        
        viewLogin.onRequestPatientAccount.addObserver(this);
    }
    
    @Override
    public void update() {
        modelMain.requestPatientAccount();
    }
}
