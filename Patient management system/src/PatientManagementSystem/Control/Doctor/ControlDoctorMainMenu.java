/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Doctor;

import PatientManagementSystem.Control.IObserver;
import PatientManagementSystem.Model.Data.BookingSystem.Appointment;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.User.Doctor;
import PatientManagementSystem.Model.User.User;
import PatientManagementSystem.View.Doctor.ViewDoctorMainMenu;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlDoctorMainMenu implements IObserver {
    
    private final ModelMain modelMain;
    
    ArrayList<Appointment> appointments;
    
    // Stores reference to window to hide/unhide
    private final ViewDoctorMainMenu viewDoctorMainMenu;  
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlDoctorMainMenu(ModelMain modelMain){
        
        this.modelMain = modelMain;        
        viewDoctorMainMenu = new ViewDoctorMainMenu(); 
        
        viewDoctorMainMenu.onClickAppointment.addObserver(this);
        
        Doctor loggedInDoctor = (Doctor)modelMain.getModelAccountSystem().getLoggedInAccount().getUser();
        appointments = modelMain.getModelBookingSystem().getAppointmentsWithDoctor(loggedInDoctor);
        
        viewDoctorMainMenu.fillAppointmentsTable(appointments);
        
        viewDoctorMainMenu.setVisible(true);
    }
    
    public void setVisible(boolean isVisible){
        viewDoctorMainMenu.setVisible(isVisible);
    }
    
    @Override
    public void update() {
        int selectedIndex = viewDoctorMainMenu.getSelectedAppointmentIndex();
        
        if (selectedIndex < 0) return;
        
        Appointment appointment = appointments.get(selectedIndex);
        User patient = appointment.getPatientAccount().getUser();
        
        
        String details = appointment.getScheduledDateAndTime() + " " + patient.getName() + " " +
                patient.getSurname();
        
        viewDoctorMainMenu.setSelectedAppointment(details);
    }
}
