/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model;
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
    
    private ControlAccountLogIn controlLogin;
    private ControlCreateAdminAccount controlCreateAccount;
    
    public ModelMain() {
        
        modelAccountSystem = new ModelAccountSystem();
        modelAppointmentSystem = new ModelBookingSystem();
        modelDoctorRatingSystem = new ModelDoctorRatingSystem();   
        
        logIn();
    }   
    
    public void logIn() {
        
        if (controlLogin == null)
            controlLogin = new ControlAccountLogIn(this);
        else
            controlLogin.setVisible(true);
    }
    
    public void createAdminAccount(){
        
        if (controlCreateAccount == null)
            controlCreateAccount = new ControlCreateAdminAccount(this);
        else
            controlCreateAccount.setVisible(true);
    }
}
