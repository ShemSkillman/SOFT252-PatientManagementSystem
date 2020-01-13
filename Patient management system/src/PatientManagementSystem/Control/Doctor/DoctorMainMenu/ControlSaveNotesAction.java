/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Doctor.DoctorMainMenu;

import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.Model.Data.BookingSystem.Appointment;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Doctor.ViewDoctorMainMenu;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlSaveNotesAction implements IObserver {
    
    private ModelMain modelMain;
    private ViewDoctorMainMenu viewDoctorMainMenu;
    Appointment selectedAppointment = null;
    
    public ControlSaveNotesAction(ViewDoctorMainMenu viewDoctorMainMenu, ModelMain modelMain) {
        
        this.modelMain = modelMain;
        this.viewDoctorMainMenu = viewDoctorMainMenu;
        
        viewDoctorMainMenu.onSaveNotes.addObserver(this);
    }
    
    public void setSelectedAppointment(Appointment selectedAppointment)
    {
        this.selectedAppointment = selectedAppointment;
    }
    
    @Override
    public void update() {        
        if (selectedAppointment == null) return;
        
        String notes = viewDoctorMainMenu.getNotes();
        
        modelMain.getModelBookingSystem().addNotes(selectedAppointment, notes);        
        viewDoctorMainMenu.showMessage("Notes saved", "Doctor notes saved to system.");
    }
}
