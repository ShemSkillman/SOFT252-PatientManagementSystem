/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Secretary.RemovePatient;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.View.Secretary.ViewRemovePatient;

/**
 *
 * @author Shem
 */
public class ControlRemovePatientAction implements IObserver {
    
    private ModelMain modelMain;
    ViewRemovePatient viewRemovePatient;
    
    public ControlRemovePatientAction(ModelMain modelMain, ViewRemovePatient viewRemovePatient) {
        this.modelMain = modelMain;
        this.viewRemovePatient = viewRemovePatient;
        
        viewRemovePatient.onRemovePatient.addObserver(this);
    }    
    
    // Gets inputs from text components on the form
    // Sends details to model to create the accout
    @Override
    public void update() {
        String patientId = viewRemovePatient.getSelectedPatientId();
        Account patientAccount = modelMain.getModelAccountSystem().getAccount(patientId);
        
        modelMain.getModelAccountSystem().RemoveAccount(patientAccount);
        
        viewRemovePatient.showMessage("Patient removed", "Patient with ID " + patientId + " has been removed from the system.");
    }
}

