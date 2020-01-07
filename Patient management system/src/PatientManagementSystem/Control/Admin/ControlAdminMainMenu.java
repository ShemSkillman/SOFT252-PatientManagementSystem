/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Admin;

import PatientManagementSystem.Control.Admin.AdminMainMenu.ControlAddAccountAction;
import PatientManagementSystem.Control.Admin.AdminMainMenu.ControlRemoveAccountAction;
import PatientManagementSystem.Control.Admin.AdminMainMenu.ControlViewDoctorRatingsAction;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Admin.ViewAdminMainMenu;

/**
 *
 * @author Shem
 */
public class ControlAdminMainMenu {
    
    // Stores reference to window to hide/unhide
    private final ViewAdminMainMenu viewAdminMainMenu;  
    
    private final ControlAddAccountAction controlAddAccountAction;
    private final ControlRemoveAccountAction controlRemoveAccountAction;
    private final ControlViewDoctorRatingsAction controlViewDoctorRatingsAction;
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlAdminMainMenu(ModelMain modelMain){
        
        viewAdminMainMenu = new ViewAdminMainMenu();  
        controlAddAccountAction = new ControlAddAccountAction(modelMain, viewAdminMainMenu);
        controlRemoveAccountAction = new ControlRemoveAccountAction(modelMain, viewAdminMainMenu);
        controlViewDoctorRatingsAction = new ControlViewDoctorRatingsAction(modelMain, viewAdminMainMenu);
        
        Account loggedInAccount = modelMain.getModelAccountSystem().getLoggedInAccount();
        viewAdminMainMenu.setWelcomeMessage("Hi " + loggedInAccount.getUser().getName() + " "
        + loggedInAccount.getUser().getSurname() + ",");
        
        viewAdminMainMenu.setVisible(true);
    }
    
    public void setVisible(boolean isVisible){
        viewAdminMainMenu.setVisible(isVisible);
    }
}
