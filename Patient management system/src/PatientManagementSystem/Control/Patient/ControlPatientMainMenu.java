/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Patient;

import PatientManagementSystem.IObserver;
import PatientManagementSystem.Control.Patient.PatientMainMenu.ControlDoctorRatingsAction;
import PatientManagementSystem.Control.Patient.PatientMainMenu.ControlPatientHistoryAction;
import PatientManagementSystem.Control.Patient.PatientMainMenu.ControlPatientLogOutAction;
import PatientManagementSystem.Control.Patient.PatientMainMenu.ControlRequestAccountDeletionAction;
import PatientManagementSystem.Control.Patient.PatientMainMenu.ControlRequestDoctorAppointmentAction;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.BookingSystem.Appointment;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Patient.ViewPatientMainMenu;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlPatientMainMenu implements IObserver{
    
    private final ViewPatientMainMenu viewPatientMainMenu;
    private final ModelMain modelMain;
    
    private final ControlDoctorRatingsAction controlDoctorRatingsAction;
    private final ControlRequestDoctorAppointmentAction controlRequestDoctorAppointmentAction;
    private final ControlPatientHistoryAction controlPatientHistoryAction;
    private final ControlRequestAccountDeletionAction controlRequestAccountDeletionAction;
    private final ControlPatientLogOutAction controlPatientLogOutAction;

    public ControlPatientMainMenu(ModelMain modelMain) {
        
        viewPatientMainMenu = new ViewPatientMainMenu();
        this.modelMain = modelMain;
        
        controlDoctorRatingsAction = new ControlDoctorRatingsAction(viewPatientMainMenu, modelMain);
        controlRequestDoctorAppointmentAction = new ControlRequestDoctorAppointmentAction(viewPatientMainMenu, modelMain);
        controlPatientHistoryAction = new ControlPatientHistoryAction(viewPatientMainMenu, modelMain);
        controlRequestAccountDeletionAction = new ControlRequestAccountDeletionAction(viewPatientMainMenu, modelMain);
        controlPatientLogOutAction = new ControlPatientLogOutAction(modelMain, viewPatientMainMenu);
        
        refresh();
        
        viewPatientMainMenu.setVisible(true);
    }
    
    private void refresh() {
        setWelcomeMessage();
        setAppointmentsTable();
    }
    
    private void setWelcomeMessage()
    {
        Account loggedInAccount = modelMain.getModelAccountSystem().getLoggedInAccount();
        String fullName = loggedInAccount.getUser().getName() + " " + loggedInAccount.getUser().getSurname();
        viewPatientMainMenu.setWelcomeMessage(fullName);
    }
    
    private void setAppointmentsTable()
    {
        Account loggedInPatient = modelMain.getModelAccountSystem().getLoggedInAccount();
        ArrayList<Appointment> appointments = modelMain.getModelBookingSystem().getAppointmentsForPatient(loggedInPatient);
        viewPatientMainMenu.fillBookingsTable(appointments, modelMain.getModelAccountSystem());
    }
    
    @Override
    public void update() {
        setAppointmentsTable();
    }
    
    public void setVisible(boolean isVisible){
        
        if (isVisible) refresh();
        
        viewPatientMainMenu.setVisible(isVisible);
    }
}
