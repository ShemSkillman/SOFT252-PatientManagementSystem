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
import PatientManagementSystem.View.Event;

/**
 *
 * @author Shem
 */
public class ModelPatientRequestSystem {
    
    private final ArrayList<ICommand> requests = new ArrayList<ICommand>();
    
    public Event onUpdateRequests = new Event();
    
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
        
        onUpdateRequests.invoke();
    }
    
    public void requestAppointment(Account fromPatient, String dateAndTime, Doctor withDoctor) {
        
        AppointmentRequest appointmentRequest = new AppointmentRequest(fromPatient, dateAndTime, withDoctor, modelBookingSystem);
        
        requests.add(appointmentRequest);
        
        modelAccountHistoryTracker.recordAction("Requested appointment with doctor " + withDoctor.getName() + " " + withDoctor.getSurname());
        
        onUpdateRequests.invoke();
    }
    
    public void requestAccountTermination(Account patientAccount, String reason) {
        
        TerminationRequest terminationRequest = new TerminationRequest(patientAccount, reason, modelAccountSystem);
        
        requests.add(terminationRequest);
        
        modelAccountHistoryTracker.recordAction("Requested termination of account");
        
        onUpdateRequests.invoke();
    }
    
    public void approveRequest(ICommand request) {
        request.execute();
        if (requests.contains(request)) requests.remove(request);
        
        onUpdateRequests.invoke();
    }
    
    public void rejectRequest(ICommand request) {
        if (requests.contains(request)) requests.remove(request);
        
        onUpdateRequests.invoke();
    }
    
    public ArrayList<ICommand> getAllRequests() {
        return requests;
    }
}
