/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Patient.RequestAccountDeletion;

import PatientManagementSystem.Control.IObserver;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Patient.ViewRequestAccountDeletion;

/**
 *
 * @author Shem
 */
public class ControlRequestAccountDeletionAction implements IObserver {
    
    private ModelMain modelMain;
    private ViewRequestAccountDeletion viewRequestAccountDeletion;
    
    public ControlRequestAccountDeletionAction(ViewRequestAccountDeletion viewRequestAccountDeletion, ModelMain modelMain) {
        
        this.modelMain = modelMain;
        this.viewRequestAccountDeletion = viewRequestAccountDeletion;
        
        viewRequestAccountDeletion.onSendRequest.addObserver(this);
    }
    
    @Override
    public void update() {
        
        Account loggedInPatient = modelMain.getModelAccountSystem().getLoggedInAccount();
        String reason = viewRequestAccountDeletion.getReason();
        
        modelMain.getModelPatientRequestSystem().requestAccountTermination(loggedInPatient, reason);
        
        viewRequestAccountDeletion.showMessage("Termination request sent", "Request for termination of account sent and will be reviewed shortly.");
    }
}
