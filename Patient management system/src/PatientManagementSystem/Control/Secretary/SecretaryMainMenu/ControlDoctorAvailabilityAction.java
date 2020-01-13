/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Secretary.SecretaryMainMenu;

import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.View.Secretary.ViewSecretaryMainMenu;

/**
 *
 * @author Shem
 */
public class ControlDoctorAvailabilityAction implements IObserver {
    
    private ModelMain modelMain;
    ViewSecretaryMainMenu viewSecretaryMainMenu;
    
    public ControlDoctorAvailabilityAction(ModelMain modelMain, ViewSecretaryMainMenu viewSecretaryMainMenu) {
        this.modelMain = modelMain;
        this.viewSecretaryMainMenu = viewSecretaryMainMenu;
        
        viewSecretaryMainMenu.onSeeDoctorAvailability.addObserver(this);
    }    
    
    // Gets inputs from text components on the form
    // Sends details to model to create the accout
    @Override
    public void update() {
        modelMain.secretaryDoctorAvailability();
    }
}
