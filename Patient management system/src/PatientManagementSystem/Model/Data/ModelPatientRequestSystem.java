/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.PatientRequestSystem.AccountRequest;
import PatientManagementSystem.Model.Data.PatientRequestSystem.AppointmentRequest;
import PatientManagementSystem.Model.Data.PatientRequestSystem.TerminationRequest;
import PatientManagementSystem.Model.ICommand;
import java.util.ArrayList;
import PatientManagementSystem.Model.User.*;

/**
 *
 * @author Shem
 */
public class ModelPatientRequestSystem {
    
    private final ArrayList<ICommand> requests = new ArrayList<ICommand>();
    
    private final ModelAccountSystem modelAccountSystem;
    private final ModelBookingSystem modelBookingSystem;
    private final ModelAccountHistoryTracker modelAccountHistoryTracker;

    public ModelPatientRequestSystem(ModelAccountSystem modelAccountSystem, ModelBookingSystem modelBookingSystem, 
            ModelAccountHistoryTracker modelAccountHistoryTracker) {
        this.modelAccountSystem = modelAccountSystem;
        this.modelBookingSystem = modelBookingSystem;
        this.modelAccountHistoryTracker = modelAccountHistoryTracker;
    }   
    
    public void requestAccount(Patient requestingPatient, String password) {
        
        AccountRequest accountRequest = new AccountRequest(requestingPatient, password, modelAccountSystem);
        requests.add(accountRequest);
    }
    
    public void requestAppointment(Account fromPatient, String timeAvailability, Doctor withDoctor) {
        
        AppointmentRequest appointmentRequest = new AppointmentRequest(fromPatient, timeAvailability, withDoctor, modelBookingSystem);
        
        requests.add(appointmentRequest);
        
        modelAccountHistoryTracker.recordAction("Requested appointment with doctor " + withDoctor.getName() + " " + withDoctor.getSurname());
    }
    
    public void requestAccountTermination(Account patientAccount, String reason) {
        
        TerminationRequest terminationRequest = new TerminationRequest(patientAccount, reason, modelAccountSystem);
        
        requests.add(terminationRequest);
        
        modelAccountHistoryTracker.recordAction("Requested termination of account");
    }
}
