/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Patient.PatientMainMenu;

import PatientManagementSystem.Control.IObserver;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Patient.ViewPatientMainMenu;

/**
 *
 * @author Shem
 */
public class ControlRequestAccountDeletionAction implements IObserver {
    
    private ModelMain modelMain;
    
    public ControlRequestAccountDeletionAction(ViewPatientMainMenu viewPatientMainMenu, ModelMain modelMain) {
        
        this.modelMain = modelMain;
        viewPatientMainMenu.onRequestAccountDeletion.addObserver(this);
    }
    
    @Override
    public void update() {
        modelMain.patientRequestAccountDeletion();
    }
}
