/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Patient.RequestPatientAccount;
import PatientManagementSystem.Control.*;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.User.Gender;
import PatientManagementSystem.Model.User.Patient;
import PatientManagementSystem.View.Patient.ViewRequestPatientAccount;

/**
 *
 * @author Shem
 */
public class ControlSendPatientRequestAction implements IObserver {
    
    private ModelMain modelMain;
    private ViewRequestPatientAccount viewRequestPatientAccount;
    
    public ControlSendPatientRequestAction(ModelMain modelMain, ViewRequestPatientAccount viewRequestPatientAccount) {
        
        this.modelMain = modelMain;
        this.viewRequestPatientAccount = viewRequestPatientAccount;
        
        viewRequestPatientAccount.onSendPatientRequest.addObserver(this);
    }    
    
    @Override
    public void update() {
        
        String firstName = viewRequestPatientAccount.getFirstName();
        String surname = viewRequestPatientAccount.getSurname();
        String address = viewRequestPatientAccount.getAddress();
        int age = viewRequestPatientAccount.getAge();
        Gender gender = viewRequestPatientAccount.getGender();
        String password = viewRequestPatientAccount.getPassword();
        
        Patient patient = new Patient(firstName, surname, address, age, gender);
        
        modelMain.getModelPatientRequestSystem().requestAccount(patient, password);
        
        viewRequestPatientAccount.showMessage("Account request sent", "Request for patient account creation has been sent and will be reviewed shortly.");
        viewRequestPatientAccount.setVisible(false);
    }
}
