/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Admin.AddAccount;

import PatientManagementSystem.Control.IObserver;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.ModelAccountSystem;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.User.Doctor;
import PatientManagementSystem.Model.User.Secretary;
import PatientManagementSystem.Model.User.User;
import PatientManagementSystem.View.Admin.ViewAddAccount;

/**
 *
 * @author Shem
 */
public class ControlAddAccountAction implements IObserver {
    
    private ModelMain modelMain;
    private ViewAddAccount viewAddAccount;
    
    public ControlAddAccountAction(ModelMain modelMain, ViewAddAccount viewAddAccount) {
        this.modelMain = modelMain;
        this.viewAddAccount = viewAddAccount;
        
        viewAddAccount.onAddAccount.addObserver(this);
    }    
    
    // Gets inputs from text components on the form
    // Sends details to model to create the accout
    @Override
    public void update() {
        
        ModelAccountSystem modelAccountSystem = modelMain.getModelAccountSystem();
        
        User newUser = null;        
        
        if (viewAddAccount.getAccountType().compareTo("Secretary") == 0) {
            newUser = new Secretary(viewAddAccount.getFirstName(), viewAddAccount.getSurname(), viewAddAccount.getAddress());
        }
        else if (viewAddAccount.getAccountType().compareTo("Doctor") == 0) {
            newUser = new Doctor(viewAddAccount.getFirstName(), viewAddAccount.getSurname(), viewAddAccount.getAddress());
        }        
        
        Account createdAccount = modelAccountSystem.CreateAccount(newUser, viewAddAccount.getPassword());
        
        viewAddAccount.showMessage("Account added successfully", "New " + viewAddAccount.getAccountType() + " account has been added to the"
                + " system with ID " + createdAccount.getId() + " and password " + viewAddAccount.getPassword());
    }
}
