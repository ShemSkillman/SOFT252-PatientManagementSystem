/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Patient.PatientMainMenu;

import PatientManagementSystem.IObserver;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Patient.ViewPatientMainMenu;

/**
 *
 * @author Shem
 */
public class ControlPatientLogOutAction implements IObserver {
    
    private ModelMain modelMain;
    ViewPatientMainMenu viewPatientMainMenu;
    
    public ControlPatientLogOutAction(ModelMain modelMain, ViewPatientMainMenu viewPatientMainMenu) {
        this.modelMain = modelMain;
        this.viewPatientMainMenu = viewPatientMainMenu;
        
        viewPatientMainMenu.onLogOut.addObserver(this);
    }    
    
    // Gets inputs from text components on the form
    // Sends details to model to create the accout
    @Override
    public void update() {
        modelMain.getModelAccountSystem().logOut();
        viewPatientMainMenu.setVisible(false);
        modelMain.logIn();
    }
}
