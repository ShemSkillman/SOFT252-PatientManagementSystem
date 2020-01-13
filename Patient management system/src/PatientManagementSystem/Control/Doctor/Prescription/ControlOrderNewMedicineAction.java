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
public class ControlOrderNewMedicineAction implements IObserver {
    
    private ModelMain modelMain;
    private ViewPrescription viewPrescription;
    
    public ControlOrderNewMedicineAction(ViewPrescription viewPrescription, ModelMain modelMain) {
        
        this.modelMain = modelMain;
        this.viewPrescription = viewPrescription;
        
        viewPrescription.onOrderNewMedicine.addObserver(this);
    }
    
    @Override
    public void update() {        
        modelMain.doctorOrderNewMedicine();
    }
}
