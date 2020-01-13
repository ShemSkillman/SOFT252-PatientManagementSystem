/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Doctor;

import PatientManagementSystem.Control.Doctor.Prescription.ControlOrderNewMedicineAction;
import PatientManagementSystem.Control.Doctor.Prescription.ControlRequestPrescription;
import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.BookingSystem.Appointment;
import PatientManagementSystem.Model.Data.PrescriptionSystem.Prescription;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Doctor.ViewPrescription;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlPrescription implements IObserver {
    
    private final ModelMain modelMain;
    private final ViewPrescription viewPrescription;
    
    private final ControlOrderNewMedicineAction controlOrderNewMedicineAction;
    private final ControlRequestPrescription controlRequestPrescription;
    
    public ControlPrescription(ModelMain modelMain){        
        
        this.modelMain = modelMain;
        viewPrescription = new ViewPrescription();
        controlOrderNewMedicineAction = new ControlOrderNewMedicineAction(viewPrescription, modelMain);
        controlRequestPrescription = new ControlRequestPrescription(viewPrescription, modelMain);
        
        viewPrescription.onSelectPatient.addObserver(this);
        
        refresh();
        
        viewPrescription.setVisible(true);
    }
    
    private void refresh() {
        Account loggedInAccount = modelMain.getModelAccountSystem().getLoggedInAccount();
        ArrayList<Appointment> appointments = modelMain.getModelBookingSystem().getAppointmentsWithDoctor(loggedInAccount);
        
        ArrayList<String> patientIds = new ArrayList();
        
        for (int i = 0; i < appointments.size(); i++) {
            
            String patientId = appointments.get(i).getPatientId();
            
            if (!patientIds.contains(patientId)) patientIds.add(patientId);
        }
        
        viewPrescription.setPatients(patientIds);
        viewPrescription.setMedicines(modelMain.getModelPrescriptionSystem().getMedicineNames());
        
        update();
    }
    
    public void setVisible(boolean isVisible){
        
        if (isVisible) refresh();
        viewPrescription.setVisible(isVisible);
    }
    
    @Override
    public void update() {
        String selectedPatientId = viewPrescription.getSelectedPatientId();
        
        ArrayList<Prescription> prescriptions = modelMain.getModelPrescriptionSystem().getPrescriptionsForPatient(selectedPatientId);
        
        viewPrescription.fillPrescriptionsTable(prescriptions);                
    }
}
