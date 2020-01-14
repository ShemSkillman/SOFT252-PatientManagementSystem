/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Admin;

import PatientManagementSystem.Control.Admin.DoctorRatings.ControlSaveSummaryAction;
import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.DoctorRatingSystem.DoctorRating;
import PatientManagementSystem.Model.Data.DoctorRatingSystem.PatientFeedback;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Admin.ViewAdminDoctorRatings;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlAdminDoctorRatings implements IObserver {
    
    ModelMain modelMain;
    
    // Stores reference to window to hide/unhide
    private final ViewAdminDoctorRatings viewAdminDoctorRatings;  
    private final ControlSaveSummaryAction controlSaveSummaryAction;
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlAdminDoctorRatings(ModelMain modelMain){
        
        this.modelMain = modelMain;
        viewAdminDoctorRatings = new ViewAdminDoctorRatings();  
        controlSaveSummaryAction = new ControlSaveSummaryAction(modelMain, viewAdminDoctorRatings);
        viewAdminDoctorRatings.onSelectNewDoctor.addObserver(this);
        modelMain.getModelDoctorRatingSystem().onUpdateDoctorRatings.addObserver(this);
        
        refresh();
        
        viewAdminDoctorRatings.setVisible(true);
    }
    
    private void refresh() {
        ArrayList<DoctorRating> ratings = modelMain.getModelDoctorRatingSystem().getRatedDoctors();
        ArrayList<Account> accountsWithRating = new ArrayList<Account>();
        
        if (ratings.size() < 1) 
        {
            viewAdminDoctorRatings.enableSaveSummaryButton(false);
            return;
        }
        
        viewAdminDoctorRatings.enableSaveSummaryButton(true);
        
        for(int i = 0; i < ratings.size(); i++) 
        {
            accountsWithRating.add(modelMain.getModelAccountSystem().getAccount(ratings.get(i).getDoctorId()));
        }
        
        ArrayList<String> doctorNames = modelMain.getModelAccountSystem().getAccountNames(accountsWithRating);
        
        viewAdminDoctorRatings.setDoctorNames(doctorNames);
        
        updateDoctorInfo();
    }
    
    @Override
    public void update() {
        refresh();
    }
    
    private void updateDoctorInfo() {
        
        String doctorId = viewAdminDoctorRatings.getSelectedDoctorId();
        
        if (doctorId == null || doctorId.isBlank()) return;
        
        Account doctorAccount = modelMain.getModelAccountSystem().getAccount(doctorId);
        DoctorRating rating = modelMain.getModelDoctorRatingSystem().findDoctorRating(doctorAccount.getId());       
        
        viewAdminDoctorRatings.setAverageFiveStarRating(rating.getAverageFiveStarRating());
        viewAdminDoctorRatings.setSummary(rating.getFeedbackSummary());
        
        ArrayList<PatientFeedback> allFeedback = rating.getAllPatientFeedback();
        
        viewAdminDoctorRatings.fillFeedbackTable(allFeedback, modelMain.getModelAccountSystem());
    }
    
    public void setVisible(boolean isVisible){
        
        if (isVisible) refresh();
        
        viewAdminDoctorRatings.setVisible(isVisible);
    }
}
