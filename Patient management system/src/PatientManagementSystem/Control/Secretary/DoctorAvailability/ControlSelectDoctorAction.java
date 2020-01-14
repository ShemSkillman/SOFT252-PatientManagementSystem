/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Secretary.DoctorAvailability;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.BookingSystem.Appointment;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.User.User;
import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.View.Secretary.ViewDoctorAvailability;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlSelectDoctorAction implements IObserver {
    
    private ModelMain modelMain;
    ViewDoctorAvailability viewDoctorAvailability;
    
    public ControlSelectDoctorAction(ModelMain modelMain, ViewDoctorAvailability viewDoctorAvailability) {
        this.modelMain = modelMain;
        this.viewDoctorAvailability = viewDoctorAvailability;
        
        update();
        
        viewDoctorAvailability.onSelectDoctor.addObserver(this);
    }    
    
    // Gets inputs from text components on the form
    // Sends details to model to create the accout
    @Override
    public void update() {        
        String doctorId = viewDoctorAvailability.getSelectedDoctorId();
        
        if (doctorId == null) return;
        
        Account doctorAccount = modelMain.getModelAccountSystem().getAccount(doctorId);
        ArrayList<Appointment> appointments = modelMain.getModelBookingSystem().getAppointmentsWithDoctor(doctorAccount);
        
        ArrayList<String> appointmentTimeAndDates = new ArrayList();
        ArrayList<String> patientNames = new ArrayList();
        
        for (Appointment appointment : appointments)
        {
            appointmentTimeAndDates.add(appointment.getScheduledDateAndTime());
            User patient = modelMain.getModelAccountSystem().getAccount(appointment.getPatientId()).getUser();
            
            String patientName = appointment.getPatientId() + " " + patient.getName() + " " + patient.getSurname();
            patientNames.add(patientName);
        }
        
        viewDoctorAvailability.fillBookingsTable(appointmentTimeAndDates, patientNames);
    }
}
