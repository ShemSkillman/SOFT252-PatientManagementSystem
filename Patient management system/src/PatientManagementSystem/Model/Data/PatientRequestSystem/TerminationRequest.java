/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.PatientRequestSystem;

import PatientManagementSystem.Model.Data.ModelAccountSystem;
import PatientManagementSystem.Model.ICommand;
import PatientManagementSystem.Model.User.User;

/**
 *
 * @author Shem
 */
public class TerminationRequest implements ICommand {
    
    private final ModelAccountSystem modelAccountSystem;
    
    private final String patientId;
    private final String reason;

    public String getPatientId() {
        return patientId;
    }

    public String getReason() {
        return reason;
    }

    
    
    public TerminationRequest(String patientId, String reason, ModelAccountSystem modelAccountSystem) {
        
        this.modelAccountSystem = modelAccountSystem;
        this.patientId = patientId;
        this.reason = reason;
    }
    
    @Override
    public void execute() {
        modelAccountSystem.RemoveAccount(modelAccountSystem.getAccount(patientId));
    }
    
    @Override
    public String getDescription() {
        User patient = modelAccountSystem.getAccount(patientId).getUser();
        return "Patient request for account termination\nPatient ID: " + patientId + "\nPatient name: " + 
                patient.getName() + " " + patient.getSurname() + "\nReason for termination: " + 
                reason;
    }
    
    @Override
    public String getShortDescription() {
        return "Patient account termination request";
    }
    
    @Override 
    public String getSenderId()
    {
        return patientId;
    }
}
