/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Doctor;

import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.Model.Data.AccountHistoryTracker.PerformedAction;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.BookingSystem.Appointment;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Doctor.ViewInspectPatientHistory;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlInspectPatientHistory implements IObserver {
    
    private ModelMain modelMain;
    private final ViewInspectPatientHistory viewInspectPatientHistory;
    
    public ControlInspectPatientHistory(ModelMain modelMain){        
        
        this.modelMain = modelMain;
        viewInspectPatientHistory = new ViewInspectPatientHistory();
        
        viewInspectPatientHistory.onSelectPatient.addObserver(this);
        
        refresh();
        
        viewInspectPatientHistory.setVisible(true);
    }
    
    private void refresh() {
        Account loggedInAccount = modelMain.getModelAccountSystem().getLoggedInAccount();
        ArrayList<Appointment> appointments = modelMain.getModelBookingSystem().getAppointmentsWithDoctor(loggedInAccount);
        
        ArrayList<String> patientIds = new ArrayList();
        
        for (int i = 0; i < appointments.size(); i++) {
            
            String patientId = appointments.get(i).getPatientId();
            
            if (!patientIds.contains(patientId)) patientIds.add(patientId);
        }
        
        viewInspectPatientHistory.setPatients(patientIds);
        
        update();
    }
    
    public void setVisible(boolean isVisible){
        refresh();
        
        viewInspectPatientHistory.setVisible(isVisible);
    }
    
    @Override
    public void update() {
        String selectedPatientId = viewInspectPatientHistory.getSelectedPatientId();
        ArrayList<PerformedAction> history = modelMain.getModelAccountHistoryTracker().getAccountHistory(selectedPatientId);
        
        viewInspectPatientHistory.fillPatientHistoryTable(history);
    }
}
