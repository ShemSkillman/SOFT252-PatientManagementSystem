/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Secretary;

import PatientManagementSystem.Control.Secretary.RemovePatient.ControlRemovePatientAction;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.User.Role;
import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.View.Secretary.ViewRemovePatient;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlRemovePatient implements IObserver {
    
    ModelMain modelMain;
    
    // Stores reference to window to hide/unhide
    private final ViewRemovePatient viewRemovePatient;  
    private final ControlRemovePatientAction controlRemovePatientAction;
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlRemovePatient(ModelMain modelMain){
        
        this.modelMain = modelMain;
        viewRemovePatient = new ViewRemovePatient();     
        controlRemovePatientAction = new ControlRemovePatientAction(modelMain, viewRemovePatient);     
        
        modelMain.getModelAccountSystem().onUpdateAccounts.addObserver(this);
        
        refresh();
        
        viewRemovePatient.setVisible(true);
    }
    
    private void refresh() {
        ArrayList<Account> patientAccounts = modelMain.getModelAccountSystem().getAccountsOfTypeRole(Role.Patient);
        
        ArrayList<String> patientNames = new ArrayList();
        
        for (var account : patientAccounts)
        {
            String patientName = account.getId() + " " + account.getUser().getName() +
                    " " + account.getUser().getSurname();
            
            patientNames.add(patientName);
        }
        
        viewRemovePatient.setPatients(patientNames);
    }
    
    public void setVisible(boolean isVisible){
        
        if (isVisible) refresh();
        
        viewRemovePatient.setVisible(isVisible);
    }
    
    @Override
    public void update() {
        refresh();
    }
}
