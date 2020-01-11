/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.PatientRequestSystem;

import PatientManagementSystem.Model.ICommand;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.User.User;

/**
 *
 * @author Shem
 */
public class AppointmentRequest implements ICommand {
    
    private final ModelMain modelMain;
    
    private final String patientId;
    private final String dateAndTime;
    private final String doctorId;   
    
    public AppointmentRequest(String patientId, String dateAndTime, String doctorId, ModelMain modelMain) {
        
        this.modelMain = modelMain;
        
        this.patientId = patientId;
        this.dateAndTime = dateAndTime;
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public String getDoctorId() {
        return doctorId;
    }
    
    @Override
    public void execute() {
        
        modelMain.getModelBookingSystem().bookAppointment(patientId, doctorId, dateAndTime);
    }
    
    @Override
    public String getDescription() {
        User patient = modelMain.getModelAccountSystem().getAccount(patientId).getUser();
        User doctor = modelMain.getModelAccountSystem().getAccount(doctorId).getUser();
        
        return "Patient request for appointment\nPatient ID: " + patientId + "\nPatient name: " + 
                patient.getName() + " " + patient.getSurname() + "\nDoctor: " + doctor.getName() + " "+
                doctor.getSurname() + "\nDate and Time: " + dateAndTime;
    }
    
    @Override
    public String getShortDescription() {
        return "Patient appointment request";
    }
    
    @Override 
    public String getSenderId()
    {
        return patientId;
    }
}
