/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model;
import PatientManagementSystem.Control.Admin.ControlAddAccount;
import PatientManagementSystem.Control.Admin.ControlAdminDoctorRatings;
import PatientManagementSystem.Control.Admin.ControlAdminMainMenu;
import PatientManagementSystem.Control.Patient.ControlPatientDoctorRatings;
import PatientManagementSystem.Control.Patient.ControlRequestPatientAccount;
import PatientManagementSystem.Control.Patient.ControlPatientMainMenu;
import PatientManagementSystem.Control.Admin.ControlCreateAdminAccount;
import PatientManagementSystem.Control.Admin.ControlRemoveAccount;
import PatientManagementSystem.Control.Doctor.ControlDoctorMainMenu;
import PatientManagementSystem.Control.Patient.ControlPatientHistory;
import PatientManagementSystem.Control.Patient.ControlRateDoctor;
import PatientManagementSystem.Control.Patient.ControlRequestAccountDeletion;
import PatientManagementSystem.Control.Shared.ControlAccountLogIn;
import PatientManagementSystem.Model.Data.*;
import PatientManagementSystem.Control.Patient.ControlRequestDoctorAppointment;
import PatientManagementSystem.Control.Secretary.ControlSecretaryMainMenu;
/**
 *
 * @author Shem
 */
public class ModelMain {
    
    private final ModelAccountSystem modelAccountSystem;
    private final ModelBookingSystem modelBookingSystem;
    private final ModelDoctorRatingSystem modelDoctorRatingSystem;
    private final ModelPatientRequestSystem modelPatientRequestSystem;
    private final ModelAccountHistoryTracker modelAccountHistoryTracker;
    
    private ControlAccountLogIn controlAccountLogIn;
    
    private ControlRequestPatientAccount controlRequestPatientAccount;
    private ControlPatientMainMenu controlPatientMainMenu;
    private ControlPatientDoctorRatings controlPatientDoctorRatings;
    private ControlRequestDoctorAppointment controlRequestDoctorAppointment;
    private ControlPatientHistory controlPatientHistory;
    private ControlRequestAccountDeletion controlRequestAccountDeletion;
    private ControlRateDoctor controlRateDoctor;
    
    private ControlCreateAdminAccount controlCreateAdminAccount;
    private ControlAdminMainMenu controlAdminMainMenu;
    private ControlAddAccount controlAddAccount;
    private ControlRemoveAccount controlRemoveAccount;
    private ControlAdminDoctorRatings controlAdminDoctorRatings;
    
    private ControlDoctorMainMenu controlDoctorMainMenu;
    private ControlSecretaryMainMenu controlSecretaryMainMenu;
    
    public ModelMain() {
        
        modelAccountHistoryTracker = new ModelAccountHistoryTracker();
        modelAccountSystem = new ModelAccountSystem(modelAccountHistoryTracker);
        modelAccountHistoryTracker.setModelAccountSystem(modelAccountSystem);        
        modelBookingSystem = new ModelBookingSystem(modelAccountHistoryTracker, modelAccountSystem);
        modelDoctorRatingSystem = new ModelDoctorRatingSystem(modelAccountSystem, modelAccountHistoryTracker);
        modelPatientRequestSystem = new ModelPatientRequestSystem(modelAccountSystem, modelBookingSystem, modelAccountHistoryTracker);
    }   
    
    public void logIn() {
        
        if (controlAccountLogIn == null)
            controlAccountLogIn = new ControlAccountLogIn(this);
        else
            controlAccountLogIn.setVisible(true);
    }
    
    public void createAdminAccount(){
        
        if (controlCreateAdminAccount == null)
            controlCreateAdminAccount = new ControlCreateAdminAccount(this);
        else
            controlCreateAdminAccount.setVisible(true);
    }
    
    public void requestPatientAccount() {
        
        if (controlRequestPatientAccount == null)
            controlRequestPatientAccount = new ControlRequestPatientAccount(this);
        else
            controlRequestPatientAccount.setVisible(true);
    }
    
    public void patientMainMenu() {
        if (controlPatientMainMenu == null)
            controlPatientMainMenu = new ControlPatientMainMenu(this);
        else
            controlPatientMainMenu.setVisible(true);
    }
    
    public void patientDoctorRatings() {
        if (controlPatientDoctorRatings == null)
            controlPatientDoctorRatings = new ControlPatientDoctorRatings(this);
        else
            controlPatientDoctorRatings.setVisible(true);
    }
    
    public void patientRequestDoctorAppointment()
    {
        if (controlRequestDoctorAppointment == null)
            controlRequestDoctorAppointment = new ControlRequestDoctorAppointment(this);
        else
            controlRequestDoctorAppointment.setVisible(true);
    }
    
    public void patientHistory()
    {
        if (controlPatientHistory == null)
            controlPatientHistory = new ControlPatientHistory(this);
        else
            controlPatientHistory.setVisible(true);
    }
    
    public void patientRequestAccountDeletion()
    {
        if (controlRequestAccountDeletion == null)
            controlRequestAccountDeletion = new ControlRequestAccountDeletion(this);
        else
            controlRequestAccountDeletion.setVisible(true);
    }
    
    public void patientRateDoctor()
    {
        if (controlRateDoctor == null)
            controlRateDoctor = new ControlRateDoctor(this);
        else
            controlRateDoctor.setVisible(true);
    }
    
    public void adminMainMenu() {
        if (controlAdminMainMenu == null)
            controlAdminMainMenu = new ControlAdminMainMenu(this);
        else
            controlAdminMainMenu.setVisible(true);
    }
    
    public void adminAddAccount() {
        if (controlAddAccount == null)
            controlAddAccount = new ControlAddAccount(this);
        else
            controlAddAccount.setVisible(true);
    }
    
    public void adminRemoveAccount() {
        if (controlRemoveAccount == null)
            controlRemoveAccount = new ControlRemoveAccount(this);
        else
            controlRemoveAccount.setVisible(true);
    }
    
    public void adminViewDoctorRatings() {
        if (controlAdminDoctorRatings == null)
            controlAdminDoctorRatings = new ControlAdminDoctorRatings(this);
        else
            controlAdminDoctorRatings.setVisible(true);
    }
    
    public void doctorMainMenu() {
        if (controlDoctorMainMenu == null)
            controlDoctorMainMenu = new ControlDoctorMainMenu(this);
        else
            controlDoctorMainMenu.setVisible(true);
    }
    
    public void secretaryMainMenu() {
        if (controlSecretaryMainMenu == null)
            controlSecretaryMainMenu = new ControlSecretaryMainMenu(this);
        else
            controlSecretaryMainMenu.setVisible(true);
    }

    public ModelAccountSystem getModelAccountSystem() {
        return modelAccountSystem;
    }

    public ModelBookingSystem getModelBookingSystem() {
        return modelBookingSystem;
    }

    public ModelDoctorRatingSystem getModelDoctorRatingSystem() {
        return modelDoctorRatingSystem;
    }

    public ModelPatientRequestSystem getModelPatientRequestSystem() {
        return modelPatientRequestSystem;
    }   

    public ModelAccountHistoryTracker getModelAccountHistoryTracker() {
        return modelAccountHistoryTracker;
    }
    
    
}
