/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Patient;

import PatientManagementSystem.Control.Patient.RequestDoctorAppointment.ControlRequestDoctorAppointmentAction;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.User.Role;
import PatientManagementSystem.View.Patient.ViewRequestDoctorAppointment;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlRequestDoctorAppointment {
    
    private final ModelMain modelMain;
    
    // Stores reference to window to hide/unhide
    private final ViewRequestDoctorAppointment viewRequestDoctorAppointment;    
    
    private final ControlRequestDoctorAppointmentAction controlRequestDoctorAppointmentAction;
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlRequestDoctorAppointment(ModelMain modelMain){
        this.modelMain = modelMain;
        viewRequestDoctorAppointment = new ViewRequestDoctorAppointment();        
        controlRequestDoctorAppointmentAction = new ControlRequestDoctorAppointmentAction(viewRequestDoctorAppointment, modelMain);
        
        refresh();
        
        viewRequestDoctorAppointment.setVisible(true);
    }
    
    private void refresh() {
        ArrayList<Account> doctorAccounts = modelMain.getModelAccountSystem().getAccountsOfTypeRole(Role.Doctor);
        viewRequestDoctorAppointment.setDoctors(modelMain.getModelAccountSystem().getAccountNames(doctorAccounts));
    }
    
    public void setVisible(boolean isVisible){
        viewRequestDoctorAppointment.setVisible(isVisible);
    }
}
