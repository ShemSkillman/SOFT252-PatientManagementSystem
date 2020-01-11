/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.PatientRequestSystem;
import PatientManagementSystem.Model.Data.ModelAccountSystem;
import PatientManagementSystem.Model.ICommand;
import PatientManagementSystem.Model.User.Patient;

/**
 *
 * @author Shem
 */
public class AccountRequest implements ICommand {
    
    private ModelAccountSystem modelAccountSystem;
    private final Patient patientDetails;
    private final String password;
    
    public AccountRequest(Patient patientDetails, String password, 
            ModelAccountSystem modelAccountSystem) {
        this.patientDetails = patientDetails;
        this.password = password;
        this.modelAccountSystem = modelAccountSystem;
    }

    public Patient getPatientDetails() {
        return patientDetails;
    }

    public String getPassword() {
        return password;
    }    
    
    @Override
    public void execute() {
        modelAccountSystem.CreateAccount(patientDetails, password);
    }
    
    @Override
    public String getDescription() {
        return "Patient request for account\nPatient name: " + patientDetails.getName() + " " + 
                patientDetails.getSurname() + "\nPatient address: " + patientDetails.getAddress() + 
                "\nPassword: " + password;
    }
    
    @Override
    public String getShortDescription() {
        return "Patient account request";
    }
    
    @Override 
    public String getSenderId()
    {
        return null;
    }
}
