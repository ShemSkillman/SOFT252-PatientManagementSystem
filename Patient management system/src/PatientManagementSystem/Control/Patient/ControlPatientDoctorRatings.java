/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Patient;

import PatientManagementSystem.IObserver;
import PatientManagementSystem.Control.Patient.DoctorRatings.ControlRateDoctorAction;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Patient.ViewPatientDoctorRatings;

/**
 *
 * @author Shem
 */
public class ControlPatientDoctorRatings implements IObserver{
    
    ModelMain modelMain;
    
    // Stores reference to window to hide/unhide
    private final ViewPatientDoctorRatings viewPatientDoctorRatings;    
    
    // Control classes assoicated with this window
    private final ControlRateDoctorAction controlRateDoctorAction;
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlPatientDoctorRatings(ModelMain modelMain){
        
        viewPatientDoctorRatings = new ViewPatientDoctorRatings();   
        controlRateDoctorAction = new ControlRateDoctorAction(viewPatientDoctorRatings, modelMain);
        this.modelMain = modelMain;        
        modelMain.getModelDoctorRatingSystem().onUpdateDoctorRatings.addObserver(this);
        
        update();
        
        viewPatientDoctorRatings.setVisible(true);
    }
    
    @Override
    public void update()
    {
        viewPatientDoctorRatings.FillRatingsTable(modelMain.getModelDoctorRatingSystem().getRatedDoctors(), modelMain.getModelAccountSystem());
    }
    
    public void setVisible(boolean isVisible){
        
        if (isVisible) update();
        
        viewPatientDoctorRatings.setVisible(isVisible);
    }
}
