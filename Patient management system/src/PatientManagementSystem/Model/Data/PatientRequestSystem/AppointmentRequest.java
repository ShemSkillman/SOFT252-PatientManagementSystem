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
    private final String timeAvailability;
    private final Doctor withDoctor;   
    
    private int year, month, day, hour, minute;
    private boolean agreedTimeAndDate = false;
    
    public AppointmentRequest(Account fromPatient, String timeAvailability, Doctor withDoctor, ModelBookingSystem modelBookingSystem) {
        
        this.modelBookingSystem = modelBookingSystem;
        
        this.fromPatient = fromPatient;
        this.timeAvailability = timeAvailability;
        this.withDoctor = withDoctor;
    }
    
    public void setExactTimeAndDate(int year, int month, int day, int hour, int minute){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        
        agreedTimeAndDate = true;
    }

    public Account getFromPatient() {
        return fromPatient;
    }

    public String getTimeAvailability() {
        return timeAvailability;
    }

    public Doctor getWithDoctor() {
        return withDoctor;
    }
    
    @Override
    public void execute() {
        if (!agreedTimeAndDate) return;
        
        modelBookingSystem.bookAppointment(fromPatient, withDoctor, year, month, day, hour, minute);
    }
    
    
}
