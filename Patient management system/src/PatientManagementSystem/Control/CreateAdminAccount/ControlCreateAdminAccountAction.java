/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.CreateAdminAccount;
import PatientManagementSystem.Control.IObserver;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Admin.ViewCreateAdminAccount;

/**
 *
 * @author Shem
 */
public class ControlCreateAdminAccountAction implements IObserver {
    
    private ModelMain modelMain;
    
    public ControlCreateAdminAccountAction(ModelMain modelMain, ViewCreateAdminAccount viewCreateAdminAccount) {
        this.modelMain = modelMain;
        viewCreateAdminAccount.onCreateAdminAccount.addObserver(this);
    }    
    
    public void update() {
        System.out.println("CREATING ADMIN ACCOUNT");
    }
}
