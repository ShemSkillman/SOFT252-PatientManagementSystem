/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.RateDoctor;

import PatientManagementSystem.Control.IObserver;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.DoctorRatingSystem.DoctorRating;
import PatientManagementSystem.Model.Data.DoctorRatingSystem.PatientFeedback;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.User.Doctor;
import PatientManagementSystem.Model.User.Patient;
import PatientManagementSystem.View.Patient.ViewRateDoctor;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlChangeDoctorAction implements IObserver {
    
    private final ViewRateDoctor viewRateDoctor;
    private final ModelMain modelMain;
    
    private ArrayList<Account> allDoctorAccounts;

    public ControlChangeDoctorAction(ViewRateDoctor viewRateDoctor, ModelMain modelMain) {
        
        this.modelMain = modelMain;
        this.viewRateDoctor = viewRateDoctor;
        
        viewRateDoctor.onSubmitFeedback.addObserver(this); 
        setUp();       
    }
    
    private void setUp() {
        
        ArrayList<String> doctorNames = modelMain.getModelDoctorRatingSystem().getDoctorNames();        
        viewRateDoctor.setDoctors(doctorNames);        
    }
    
    @Override
    public void update() {        
        
        int selectedIndex = viewRateDoctor.getSelectedDoctorIndex();
        
        Account doctorAccount = modelMain.getModelDoctorRatingSystem().getDoctorAccount(selectedIndex);
        
        DoctorRating rating = modelMain.getModelDoctorRatingSystem().findDoctorRating((Doctor)doctorAccount.getUser());
        
        if (rating == null) return;
        
        Account loggedInAccount = modelMain.getModelAccountSystem().getLoggedInAccount();
        
        PatientFeedback feedback = rating.getPatientFeedbackForDoctor((Patient)loggedInAccount.getUser());
        
        if (feedback == null) return;
        
        viewRateDoctor.setDoctorRatingSlider(feedback.getDoctorFiveStarRating());
        viewRateDoctor.setFeedbackText(feedback.getMessage());
    
    }
    
    
}
