/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Patient.DoctorRatings;

import PatientManagementSystem.Control.IObserver;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Patient.ViewPatientDoctorRatings;

/**
 *
 * @author Shem
 */
public class ControlRateDoctorAction implements IObserver {
    
    private ModelMain modelMain;
    
    public ControlRateDoctorAction(ViewPatientDoctorRatings viewPatientDoctorRatings, ModelMain modelMain) {
        
        this.modelMain = modelMain;
        viewPatientDoctorRatings.onRateDoctor.addObserver(this);
    }
    
    @Override
    public void update() {
        modelMain.patientRateDoctor();
    }
}
