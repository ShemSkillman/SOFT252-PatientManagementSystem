/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.AccountLogIn;
import PatientManagementSystem.Control.IObserver;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.ViewLogIn;

/**
 *
 * @author Shem
 */
public class ControlRegisterAdminAction implements IObserver{    
    
    private final ModelMain modelMain;
    
    public ControlRegisterAdminAction(ModelMain modelMain, ViewLogIn viewLogin){
        this.modelMain = modelMain;
        viewLogin.onCreateAdminAccount.addObserver(this);
    }
    
    @Override
    public void update() {
        modelMain.createAdminAccount();
    }
}
