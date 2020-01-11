/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Admin.AdminMainMenu;

import PatientManagementSystem.IObserver;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Admin.ViewAdminMainMenu;

/**
 *
 * @author Shem
 */
public class ControlRemoveAccountAction implements IObserver {
    
    private ModelMain modelMain;
    
    public ControlRemoveAccountAction(ModelMain modelMain, ViewAdminMainMenu viewAdminMainMenu) {
        this.modelMain = modelMain;
        
        viewAdminMainMenu.onRemoveAccount.addObserver(this);
    }    
    
    // Gets inputs from text components on the form
    // Sends details to model to create the accout
    @Override
    public void update() {
        modelMain.adminRemoveAccount();
    }
}
