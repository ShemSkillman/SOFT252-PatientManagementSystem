/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Admin.AdminMainMenu;

import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Admin.ViewAdminMainMenu;

/**
 *
 * @author Shem
 */
public class ControlAdminLogOutAction implements IObserver{
    
    private ModelMain modelMain;
    ViewAdminMainMenu viewAdminMainMenu;
    
    public ControlAdminLogOutAction(ModelMain modelMain, ViewAdminMainMenu viewAdminMainMenu) {
        this.modelMain = modelMain;
        this.viewAdminMainMenu = viewAdminMainMenu;
        
        viewAdminMainMenu.onLogOut.addObserver(this);
    }    
    
    // Gets inputs from text components on the form
    // Sends details to model to create the accout
    @Override
    public void update() {
        modelMain.getModelAccountSystem().logOut();
        viewAdminMainMenu.setVisible(false);
        modelMain.logIn();
    }
}
