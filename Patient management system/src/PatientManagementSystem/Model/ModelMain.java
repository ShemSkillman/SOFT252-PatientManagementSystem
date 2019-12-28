/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model;
import PatientManagementSystem.Control.ControlCreateAdminAccount;
import PatientManagementSystem.Control.ControlAccountLogIn;
import PatientManagementSystem.Model.Data.*;
import PatientManagementSystem.Control.*;
/**
 *
 * @author Shem
 */
public class ModelMain {
    
    private final ModelAccountSystem modelAccountSystem;
    private final ModelBookingSystem modelAppointmentSystem;
    private final ModelDoctorRatingSystem modelDoctorRatingSystem;
    private final ModelPatientAccountRequestSystem modelPatientAccountRequestSystem;
    
    private ControlAccountLogIn controlAccountLogIn;
    private ControlCreateAdminAccount controlCreateAdminAccount;
    private ControlRequestPatientAccount controlRequestPatientAccount;
    private ControlPatientMainMenu controlPatientMainMenu;
    
    public ModelMain() {
        
        modelAccountSystem = new ModelAccountSystem();
        modelAppointmentSystem = new ModelBookingSystem();
        modelDoctorRatingSystem = new ModelDoctorRatingSystem();  
        modelPatientAccountRequestSystem = new ModelPatientAccountRequestSystem(modelAccountSystem);
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

    public ModelAccountSystem getModelAccountSystem() {
        return modelAccountSystem;
    }

    public ModelBookingSystem getModelAppointmentSystem() {
        return modelAppointmentSystem;
    }

    public ModelDoctorRatingSystem getModelDoctorRatingSystem() {
        return modelDoctorRatingSystem;
    }

    public ModelPatientAccountRequestSystem getModelPatientAccountRequestSystem() {
        return modelPatientAccountRequestSystem;
    }   
}
