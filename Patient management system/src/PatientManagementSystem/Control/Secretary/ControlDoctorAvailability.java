/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Secretary;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.BookingSystem.Appointment;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.User.Role;
import PatientManagementSystem.Model.User.User;
import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.View.Secretary.ViewDoctorAvailability;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlDoctorAvailability implements IObserver {
    
    ModelMain modelMain;
    
    // Stores reference to window to hide/unhide
    private final ViewDoctorAvailability viewDoctorAvailability;  
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlDoctorAvailability(ModelMain modelMain){
        
        this.modelMain = modelMain;
        viewDoctorAvailability = new ViewDoctorAvailability();     
        modelMain.getModelAccountSystem().onUpdateAccounts.addObserver(this);
        modelMain.getModelBookingSystem().onUpdateAppointments.addObserver(this);
        
        refresh();
        
        viewDoctorAvailability.setVisible(true);
    }
    
    public void refresh() {
        ArrayList<Account> doctorAccounts = modelMain.getModelAccountSystem().getAccountsOfTypeRole(Role.Doctor);
        
        ArrayList<String> doctorNames = new ArrayList();
        
        for (var doctorAccount : doctorAccounts)
        {
            User doctor = doctorAccount.getUser();
            
            String doctorName = doctorAccount.getId() + " " + doctor.getName() + 
                    " " + doctor.getSurname();
            
            doctorNames.add(doctorName);
        }
        
        viewDoctorAvailability.setDoctors(doctorNames);
        
        String doctorId = viewDoctorAvailability.getSelectedDoctorId();
        
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
    
    public void setVisible(boolean isVisible){
        
        if (isVisible) refresh();
        
        viewDoctorAvailability.setVisible(isVisible);
    }
    
    @Override
    public void update() {
        refresh();
    }
}
