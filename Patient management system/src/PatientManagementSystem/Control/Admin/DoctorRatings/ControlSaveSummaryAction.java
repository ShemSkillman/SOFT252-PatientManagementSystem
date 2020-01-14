/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Admin.DoctorRatings;

import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.DoctorRatingSystem.DoctorRating;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Admin.ViewAdminDoctorRatings;

/**
 *
 * @author Shem
 */
public class ControlSaveSummaryAction implements IObserver {
    
    private ModelMain modelMain;
    private ViewAdminDoctorRatings viewAdminDoctorRatings;
    
    public ControlSaveSummaryAction(ModelMain modelMain, ViewAdminDoctorRatings viewAdminDoctorRatings) {
        this.modelMain = modelMain;
        this.viewAdminDoctorRatings = viewAdminDoctorRatings;
        
        viewAdminDoctorRatings.onSaveSummary.addObserver(this);
    }    
    
    // Gets inputs from text components on the form
    // Sends details to model to create the accout
    @Override
    public void update() {
        
        String summary = viewAdminDoctorRatings.getSummary();        
        String doctorId = viewAdminDoctorRatings.getSelectedDoctorId();
        
        modelMain.getModelDoctorRatingSystem().addDoctorSummary(doctorId, summary);
        
        viewAdminDoctorRatings.showMessage("Saved doctor summary", "Summary for doctor rating has been saved to the system.");
    }
}
