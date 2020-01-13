/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Doctor.Prescription;

import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Doctor.ViewPrescription;
import PatientManagementSystem.View.EventSystem.IObserver;

/**
 *
 * @author Shem
 */
public class ControlRequestPrescription implements IObserver {
    
    private ModelMain modelMain;
    private ViewPrescription viewPrescription;
    
    public ControlRequestPrescription(ViewPrescription viewPrescription, ModelMain modelMain) {
        
        this.modelMain = modelMain;
        this.viewPrescription = viewPrescription;
        
        viewPrescription.onRequestMedicine.addObserver(this);
    }
    
    @Override
    public void update() {        
        
        String doctorId = modelMain.getModelAccountSystem().getLoggedInAccount().getId();        
        String patientId = viewPrescription.getSelectedPatientId();
        String dosage = viewPrescription.getPrescriptionDosage();
        int quantity = viewPrescription.getPrescriptionMedicineQuantity();
        String medicine = viewPrescription.getSelectedPrescriptionMedicine();       
        
        modelMain.getModelRequestSystem().requestPatientPrescription(medicine, quantity, dosage, patientId, doctorId);
        
        viewPrescription.showMessage("Prescription request sent", "Prescription request for patient has been sent a will be reviewed shortly.");
    }
}
