/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Shared.AccountLogIn;

import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.User.Role;
import PatientManagementSystem.View.ViewLogIn;

/**
 *
 * @author Shem
 */
public class ControlLogInAction implements IObserver{
    
    private final ModelMain modelMain;
    private final ViewLogIn viewLogIn;
    
    public ControlLogInAction(ModelMain modelMain, ViewLogIn viewLogin){
        this.modelMain = modelMain;
        
        this.viewLogIn = viewLogin;
        viewLogIn.onLogIn.addObserver(this);
    }
    
    @Override
    public void update() {
        String id = viewLogIn.getId();
        String password = viewLogIn.getPassword();
        
        boolean logInSuccessful = modelMain.getModelAccountSystem().logIn(id, password);
        
        if (!logInSuccessful){
            viewLogIn.showErrorMessage("User ID or password incorrect!");
            return;
        }
        
        Role role = modelMain.getModelAccountSystem().getAccountRole(id);
        
        switch (role) {
            case Patient:
                modelMain.patientMainMenu();
                break;             
            case Administrator:
                modelMain.adminMainMenu();
                break;
            case Doctor:
                modelMain.doctorMainMenu();
                break;
            case Secretary:
                modelMain.secretaryMainMenu();
                break;
        }
        
        viewLogIn.setVisible(false);
        viewLogIn.clearInput();
    }
}
