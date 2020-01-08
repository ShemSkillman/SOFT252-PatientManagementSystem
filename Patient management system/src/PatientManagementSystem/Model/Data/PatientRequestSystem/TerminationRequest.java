/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.PatientRequestSystem;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.ModelAccountSystem;
import PatientManagementSystem.Model.ICommand;

/**
 *
 * @author Shem
 */
public class TerminationRequest implements ICommand {
    
    private final ModelAccountSystem modelAccountSystem;
    
    private final Account patientAccount;
    private final String reason;

    public TerminationRequest(Account patientAccount, String reason, ModelAccountSystem modelAccountSystem) {
        
        this.modelAccountSystem = modelAccountSystem;
        this.patientAccount = patientAccount;
        this.reason = reason;
    }
    
    @Override
    public void execute() {
        modelAccountSystem.RemoveAccount(patientAccount);
    }
    
    @Override
    public String getDescription() {
        return "Patient request for account termination\nPatient ID: " + patientAccount.getId() + "\nPatient name: " + 
                patientAccount.getUser().getName() + " " + patientAccount.getUser().getSurname() + "\nReason for termination: " + 
                reason;
    }
    
    @Override
    public String getShortDescription() {
        return "Patient account termination request";
    }
}
