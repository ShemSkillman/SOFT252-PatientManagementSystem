/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Doctor;

import PatientManagementSystem.Control.Doctor.DoctorMainMenu.ControlDoctorLogOutAction;
import PatientManagementSystem.Control.Doctor.DoctorMainMenu.ControlSaveNotesAction;
import PatientManagementSystem.IObserver;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.BookingSystem.Appointment;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.User.Patient;
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
    
    private final ControlDoctorLogOutAction controlDoctorLogOutAction;
    private final ControlSaveNotesAction controlSaveNotesAction;
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlDoctorMainMenu(ModelMain modelMain){
        
        this.modelMain = modelMain;        
        viewDoctorMainMenu = new ViewDoctorMainMenu(); 
        controlDoctorLogOutAction = new ControlDoctorLogOutAction(modelMain, viewDoctorMainMenu);
        controlSaveNotesAction = new ControlSaveNotesAction(viewDoctorMainMenu, modelMain);
        
        viewDoctorMainMenu.onClickAppointment.addObserver(this);
        
        refresh();
        
        viewDoctorMainMenu.setVisible(true);
    }
    
    private void refresh(){
        Account loggedInDoctor = modelMain.getModelAccountSystem().getLoggedInAccount();
        viewDoctorMainMenu.setWelcomeMessage("Hi " + " " + loggedInDoctor.getUser().getName() + " " + 
                loggedInDoctor.getUser().getSurname() + ",");
        appointments = modelMain.getModelBookingSystem().getAppointmentsWithDoctor(loggedInDoctor);
        
        viewDoctorMainMenu.fillAppointmentsTable(appointments, modelMain.getModelAccountSystem());
        
        System.out.println(appointments.size());
        update();
    }
    
    public void setVisible(boolean isVisible){
        if (isVisible) refresh();
        
        viewDoctorMainMenu.setVisible(isVisible);
    }
    
    @Override
    public void update() {
        int selectedIndex = viewDoctorMainMenu.getSelectedAppointmentIndex();
        
        if (selectedIndex < 0 || selectedIndex >= appointments.size()) {
            viewDoctorMainMenu.setNotes("");
            viewDoctorMainMenu.EnableButtons(false);
            return;
        }
        
        viewDoctorMainMenu.EnableButtons(true);
        
        Appointment selectedAppointment = appointments.get(selectedIndex);
        controlSaveNotesAction.setSelectedAppointment(selectedAppointment);
        
        String patientId = selectedAppointment.getPatientId();
        Patient patient = (Patient)modelMain.getModelAccountSystem().getAccount(patientId).getUser();
        
        
        String details = selectedAppointment.getScheduledDateAndTime() + " " + patient.getName() + " " +
                patient.getSurname();
        
        viewDoctorMainMenu.setSelectedAppointment(details);
        viewDoctorMainMenu.setNotes(selectedAppointment.getDoctorNotes());
    }
}
