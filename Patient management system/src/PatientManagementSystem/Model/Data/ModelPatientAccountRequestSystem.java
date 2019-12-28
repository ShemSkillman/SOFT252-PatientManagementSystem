/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;
import PatientManagementSystem.Model.Data.PatientAccountRequestSystem.AccountRequest;
import java.util.ArrayList;
import PatientManagementSystem.Model.User.*;

/**
 *
 * @author Shem
 */
public class ModelPatientAccountRequestSystem {
    
    private final ArrayList<AccountRequest> accountCreationRequests = new ArrayList<AccountRequest>();
    private final ModelAccountSystem modelAccountSystem;

    public ModelPatientAccountRequestSystem(ModelAccountSystem modelAccountSystem) {
        this.modelAccountSystem = modelAccountSystem;
    }   
    
    public void RequestAccount(Patient requestingPatient, String password) {
        
        AccountRequest accountRequest = new AccountRequest(requestingPatient, password, modelAccountSystem);
        accountCreationRequests.add(accountRequest);
    }
}
