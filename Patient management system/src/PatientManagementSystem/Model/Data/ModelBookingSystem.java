/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.BookingSystem.Appointment;
import PatientManagementSystem.Model.User.Doctor;
import PatientManagementSystem.Model.User.Patient;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ModelBookingSystem {
    
    ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    
    private final ModelAccountHistoryTracker modelAccountHistoryTracker;
    private final ModelAccountSystem modelAccountSystem;

    public ModelBookingSystem(ModelAccountHistoryTracker modelAccountHistoryTracker, ModelAccountSystem modelAccountSystem) {
        this.modelAccountHistoryTracker = modelAccountHistoryTracker;
        this.modelAccountSystem = modelAccountSystem;
    }
    
    public void bookAppointment(Account patientAccount, Doctor doctorRunningAppointment, String dateAndTime) {
                
        Appointment appointment = new Appointment(patientAccount, doctorRunningAppointment, dateAndTime);
        
        appointments.add(appointment);
    }
    
    public ArrayList<Appointment> getAppointmentsWithDoctor(Doctor doctor) {
        
        ArrayList<Appointment> appointmentsWithDoctor = new ArrayList<Appointment>();
        
        for (Appointment appointment : appointments)
        {
            if (appointment.getDoctorRunningAppointment() == doctor)
            {
                appointmentsWithDoctor.add(appointment);
            }
        }
        
        return appointmentsWithDoctor;
    }
}
