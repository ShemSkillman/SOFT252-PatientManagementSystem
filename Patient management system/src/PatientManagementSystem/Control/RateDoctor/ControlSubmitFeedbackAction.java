/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.RateDoctor;

import PatientManagementSystem.Control.IObserver;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Patient.ViewRateDoctor;

/**
 *
 * @author Shem
 */
public class ControlSubmitFeedbackAction implements IObserver {
    
    private final ModelMain modelMain;

    public ControlSubmitFeedbackAction(ViewRateDoctor viewRateDoctor, ModelMain modelMain) {
        
        this.modelMain = modelMain;
        viewRateDoctor.onSubmitFeedback.addObserver(this);        
    }
    
    @Override
    public void update() {
        
    }
    
}
