/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.PatientRequestSystem;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.ModelBookingSystem;
import PatientManagementSystem.Model.ICommand;
import PatientManagementSystem.Model.User.Doctor;

/**
 *
 * @author Shem
 */
public class AppointmentRequest implements ICommand {
    
    private final ModelBookingSystem modelBookingSystem;
    
    private final Account fromPatient;
    private final String dateAndTime;
    private final Doctor withDoctor;   
    
    public AppointmentRequest(Account fromPatient, String dateAndTime, Doctor withDoctor, ModelBookingSystem modelBookingSystem) {
        
        this.modelBookingSystem = modelBookingSystem;
        
        this.fromPatient = fromPatient;
        this.dateAndTime = dateAndTime;
        this.withDoctor = withDoctor;
    }

    public Account getFromPatient() {
        return fromPatient;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public Doctor getWithDoctor() {
        return withDoctor;
    }
    
    @Override
    public void execute() {
        
        modelBookingSystem.bookAppointment(fromPatient, withDoctor, dateAndTime);
    }
    
    @Override
    public String getDescription() {
        return "Patient request for appointment\nPatient ID: " + fromPatient.getId() + "\nPatient name: " + 
                fromPatient.getUser().getName() + " " + fromPatient.getUser().getSurname() + "\nDoctor: " + withDoctor.getName() + " "+
                withDoctor.getSurname() + "\nDate and Time: " + dateAndTime;
    }
    
    @Override
    public String getShortDescription() {
        return "Patient appointment request";
    }
}
