/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Patient.RateDoctor;

import PatientManagementSystem.IObserver;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Patient.ViewRateDoctor;

/**
 *
 * @author Shem
 */
public class ControlRateDoctorAction implements IObserver {
    
    private ModelMain modelMain;
    private ViewRateDoctor viewRateDoctor;
    
    public ControlRateDoctorAction(ViewRateDoctor viewRateDoctor, ModelMain modelMain) {
        
        this.modelMain = modelMain;
        this.viewRateDoctor = viewRateDoctor;
        
        viewRateDoctor.onSubmitFeedback.addObserver(this);
    }
    
    @Override
    public void update() {
        
        String doctorId = viewRateDoctor.getChosenDoctorId();        
        var doctor = modelMain.getModelAccountSystem().getAccount(doctorId);
        int fiveStarRating = viewRateDoctor.getDoctorRating();
        String feedback = viewRateDoctor.getFeedbackText();
        
        modelMain.getModelDoctorRatingSystem().addPatientFeedback(doctor, fiveStarRating, feedback);
        
        viewRateDoctor.showMessage("Submission successful", "Successfully submitted rating for doctor!");
        
    }
}
