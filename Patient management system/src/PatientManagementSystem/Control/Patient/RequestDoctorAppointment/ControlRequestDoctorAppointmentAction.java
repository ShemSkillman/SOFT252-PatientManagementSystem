/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Patient.RequestDoctorAppointment;

import PatientManagementSystem.IObserver;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.User.Doctor;
import PatientManagementSystem.Model.User.Patient;
import PatientManagementSystem.View.Patient.ViewRequestDoctorAppointment;

/**
 *
 * @author Shem
 */
public class ControlRequestDoctorAppointmentAction implements IObserver{
    
    private ModelMain modelMain;
    private ViewRequestDoctorAppointment viewRequestDoctorAppointment;
    
    public ControlRequestDoctorAppointmentAction(ViewRequestDoctorAppointment viewRequestDoctorAppointment, ModelMain modelMain) {
        
        this.modelMain = modelMain;
        this.viewRequestDoctorAppointment = viewRequestDoctorAppointment;
        
        viewRequestDoctorAppointment.onSendAppointmentRequest.addObserver(this);
    }
    
    @Override
    public void update() {
        
        Account fromPatient = modelMain.getModelAccountSystem().getLoggedInAccount();
        String dateAndTime = viewRequestDoctorAppointment.getDateAndTime();
        
        Account doctorAccount = modelMain.getModelAccountSystem().getAccount(viewRequestDoctorAppointment.getSelectedDoctorId());
        
        modelMain.getModelPatientRequestSystem().requestAppointment(fromPatient, dateAndTime, doctorAccount);
        
        viewRequestDoctorAppointment.showMessage("Appointment request sent", "Appointment request has been sent successfully and "
                + " will be reviewed shortly.");
        
    }
}
