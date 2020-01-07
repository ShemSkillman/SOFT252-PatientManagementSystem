/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Patient;

import PatientManagementSystem.Control.Patient.PatientMainMenu.ControlDoctorRatingsAction;
import PatientManagementSystem.Control.Patient.PatientMainMenu.ControlPatientHistoryAction;
import PatientManagementSystem.Control.Patient.PatientMainMenu.ControlRequestAccountDeletionAction;
import PatientManagementSystem.Control.Patient.PatientMainMenu.ControlRequestDoctorAppointmentAction;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Patient.ViewPatientMainMenu;

/**
 *
 * @author Shem
 */
public class ControlPatientMainMenu {
    
    private final ViewPatientMainMenu viewPatientMainMenu;
    private final ModelMain modelMain;
    
    private ControlDoctorRatingsAction controlDoctorRatingsAction;
    private ControlRequestDoctorAppointmentAction controlRequestDoctorAppointmentAction;
    private ControlPatientHistoryAction controlPatientHistoryAction;
    private ControlRequestAccountDeletionAction controlRequestAccountDeletionAction;

    public ControlPatientMainMenu(ModelMain modelMain) {
        
        viewPatientMainMenu = new ViewPatientMainMenu();
        this.modelMain = modelMain;
        
        controlDoctorRatingsAction = new ControlDoctorRatingsAction(viewPatientMainMenu, modelMain);
        controlRequestDoctorAppointmentAction = new ControlRequestDoctorAppointmentAction(viewPatientMainMenu, modelMain);
        controlPatientHistoryAction = new ControlPatientHistoryAction(viewPatientMainMenu, modelMain);
        controlRequestAccountDeletionAction = new ControlRequestAccountDeletionAction(viewPatientMainMenu, modelMain);
        
        setWelcomeMessage();
        
        viewPatientMainMenu.setVisible(true);
    }
    
    private void setWelcomeMessage()
    {
        Account loggedInAccount = modelMain.getModelAccountSystem().getLoggedInAccount();
        String fullName = loggedInAccount.getUser().getName() + " " + loggedInAccount.getUser().getSurname();
        viewPatientMainMenu.setWelcomeMessage(fullName);
    }
    
    public void setVisible(boolean isVisible){
        viewPatientMainMenu.setVisible(isVisible);
    }
}
