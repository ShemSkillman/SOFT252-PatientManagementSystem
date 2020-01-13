/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Admin.CreateAdminAccount;
import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.User.*;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.Data.ModelAccountSystem;
import PatientManagementSystem.View.Admin.ViewCreateAdminAccount;

/**
 *
 * @author Shem
 */
public class ControlCreateAdminAccountAction implements IObserver {
    
    private ModelMain modelMain;
    private ViewCreateAdminAccount viewCreateAdminAccount;
    
    public ControlCreateAdminAccountAction(ModelMain modelMain, ViewCreateAdminAccount viewCreateAdminAccount) {
        this.modelMain = modelMain;
        this.viewCreateAdminAccount = viewCreateAdminAccount;
        
        viewCreateAdminAccount.onCreateAdminAccount.addObserver(this);
    }    
    
    // Gets inputs from text components on the form
    // Sends details to model to create the accout
    @Override
    public void update() {
        
        ModelAccountSystem modelAccountSystem = modelMain.getModelAccountSystem();
        
        String firstName = viewCreateAdminAccount.getFirstName();
        String surname = viewCreateAdminAccount.getSurname();
        String address = viewCreateAdminAccount.getAddress();      
        String password = viewCreateAdminAccount.getPassword();
        
        Administrator adminUser = new Administrator(firstName, surname, address);        
        
        Account createdAccount = modelAccountSystem.CreateAccount(adminUser, password);        
        
        viewCreateAdminAccount.showMessage("Created admin account", "Admin account has been created with ID: " + 
                createdAccount.getId() + " Password: " + createdAccount.getPassword());
    }
}
