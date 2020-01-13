/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Doctor;

import PatientManagementSystem.Control.Doctor.ProposeAppointment.ControlRequestAppointmentAction;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.BookingSystem.Appointment;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.User.User;
import PatientManagementSystem.View.Doctor.ViewProposeAppointment;
import PatientManagementSystem.View.EventSystem.IObserver;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlProposeAppointment implements IObserver {
    
    private final ModelMain modelMain;
    private final ViewProposeAppointment viewProposeAppointment;
    private final ControlRequestAppointmentAction  controlRequestAppointmentAction;
    
    public ControlProposeAppointment(ModelMain modelMain){        
        
        this.modelMain = modelMain;
        viewProposeAppointment = new ViewProposeAppointment();
        controlRequestAppointmentAction = new ControlRequestAppointmentAction(viewProposeAppointment, modelMain);
        
        modelMain.getModelBookingSystem().onUpdateAppointments.addObserver(this);
        refresh();
        
        viewProposeAppointment.setVisible(true);
    }
    
    private void refresh() {
        
        Account loggedInAccount = modelMain.getModelAccountSystem().getLoggedInAccount();
        ArrayList<Appointment> appointments = modelMain.getModelBookingSystem().getAppointmentsWithDoctor(loggedInAccount);
        ArrayList<String> patientNames = new ArrayList();
        
        for (int i = 0; i < appointments.size(); i++)
        {
            Appointment appointment = appointments.get(i);
            
            User patientDetails = modelMain.getModelAccountSystem().getAccount(appointment.getPatientId()).getUser();
            
            String patientName = appointment.getPatientId() + " " + patientDetails.getName() + " " + patientDetails.getSurname();
            
            if (!patientNames.contains(patientName)) patientNames.add(patientName);
        }        
        
        viewProposeAppointment.setPatients(patientNames);
        
    }
    
    public void setVisible(boolean isVisible){
        
        if (isVisible) refresh();
        
        viewProposeAppointment.setVisible(isVisible);
    }
    
    @Override
    public void update() {
        refresh();
    }
}
