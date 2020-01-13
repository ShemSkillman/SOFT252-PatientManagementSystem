/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Patient.PatientMainMenu;

import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Patient.ViewPatientMainMenu;

/**
 *
 * @author Shem
 */
public class ControlDoctorRatingsAction implements IObserver {
    
    private ModelMain modelMain;
    
    public ControlDoctorRatingsAction(ViewPatientMainMenu viewPatientMainMenu, ModelMain modelMain) {
        
        this.modelMain = modelMain;
        viewPatientMainMenu.onSeeDoctorRatings.addObserver(this);
    }
    
    @Override
    public void update() {
        modelMain.patientDoctorRatings();
    }
}
