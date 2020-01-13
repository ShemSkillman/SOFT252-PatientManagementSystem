/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Doctor.DoctorMainMenu;

import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Doctor.ViewDoctorMainMenu;

/**
 *
 * @author Shem
 */
public class ControlDoctorLogOutAction implements IObserver{
    
    private ModelMain modelMain;
    ViewDoctorMainMenu viewDoctorMainMenu;
    
    public ControlDoctorLogOutAction(ModelMain modelMain, ViewDoctorMainMenu viewDoctorMainMenu) {
        this.modelMain = modelMain;
        this.viewDoctorMainMenu = viewDoctorMainMenu;
        
        viewDoctorMainMenu.onLogOut.addObserver(this);
    }    
    
    // Gets inputs from text components on the form
    // Sends details to model to create the accout
    @Override
    public void update() {
        modelMain.getModelAccountSystem().logOut();
        viewDoctorMainMenu.setVisible(false);
        modelMain.logIn();
    }
}
