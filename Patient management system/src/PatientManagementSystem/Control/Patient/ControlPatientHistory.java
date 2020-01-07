/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Patient;

import PatientManagementSystem.Control.IObserver;
import PatientManagementSystem.Model.Data.AccountHistoryTracker.PerformedAction;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Patient.ViewPatientHistory;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlPatientHistory implements IObserver{
    
    ModelMain modelMain;
    
    // Stores reference to window to hide/unhide
    private final ViewPatientHistory viewPatientHistory;    
    
    // Control classes assoicated with this window
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlPatientHistory(ModelMain modelMain){
        
        this.modelMain = modelMain;
        viewPatientHistory = new ViewPatientHistory();     
        
        modelMain.getModelAccountHistoryTracker().onUpdateHistory.addObserver(this);
        fillHistoryTable();
        
        viewPatientHistory.setVisible(true);
    }
    
    @Override
    public void update() {
        fillHistoryTable();
    }
    
    private void fillHistoryTable() {
        Account loggedInAccount = modelMain.getModelAccountSystem().getLoggedInAccount();
        ArrayList<PerformedAction> history = modelMain.getModelAccountHistoryTracker().getAccountHistory(loggedInAccount);
        
        viewPatientHistory.fillHistoryTable(history);
    }
    
    public void setVisible(boolean isVisible){
        viewPatientHistory.setVisible(isVisible);
    }
}
