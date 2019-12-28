/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.PatientAccountRequestSystem;
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
    
    @Override
    public void execute() {
        modelAccountSystem.CreateAccount(patientDetails, password);
    }
}
