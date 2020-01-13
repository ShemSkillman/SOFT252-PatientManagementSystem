/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Doctor.ProposeAppointment;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Doctor.ViewProposeAppointment;
import PatientManagementSystem.View.EventSystem.IObserver;

/**
 *
 * @author Shem
 */
public class ControlRequestAppointmentAction implements IObserver {
    
    private ModelMain modelMain;
    private ViewProposeAppointment viewProposeAppointment;
    
    public ControlRequestAppointmentAction(ViewProposeAppointment viewProposeAppointment, ModelMain modelMain) {
        
        this.modelMain = modelMain;
        this.viewProposeAppointment = viewProposeAppointment;
        
        viewProposeAppointment.onSendAppointmentRequest.addObserver(this);
    }
    
    @Override
    public void update() {        
        
        Account patientAccount = modelMain.getModelAccountSystem().getAccount(viewProposeAppointment.getSelectedPatientId());
        Account doctorAccount = modelMain.getModelAccountSystem().getLoggedInAccount();
        String dateAndTime = viewProposeAppointment.getDateAndTime();
        
        modelMain.getModelRequestSystem().requestAppointmentForPatient(patientAccount, dateAndTime, doctorAccount);
        
        viewProposeAppointment.showMessage("Appointment request sent", "Appointment request with patient has been sent and will be reviewed shortly.");
    }
}
