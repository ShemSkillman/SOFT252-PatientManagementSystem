/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.User.Doctor;
import PatientManagementSystem.Model.Data.DoctorRatingSystem.PatientFeedback;
import PatientManagementSystem.Model.Data.DoctorRatingSystem.DoctorRating;
import java.util.ArrayList;
import PatientManagementSystem.View.Event;

/**
 *
 * @author Shem
 */
public class ModelDoctorRatingSystem {
    
    ArrayList<DoctorRating> ratedDoctors = new ArrayList<DoctorRating>(); 
    
    public Event onUpdateDoctorRatings = new Event();
    
    private final ModelAccountSystem modelAccountSystem;
    private final ModelAccountHistoryTracker modelAccountHistoryTracker;

    public ModelDoctorRatingSystem(ModelAccountSystem modelAccountSystem, ModelAccountHistoryTracker modelAccountHistoryTracker) {
        this.modelAccountSystem = modelAccountSystem;
        this.modelAccountHistoryTracker = modelAccountHistoryTracker;
    }   
    
    // Add feedback from patient to a specific doctor
    public boolean addPatientFeedback(Account doctorToRate, int fiveStarRating, String message)
    {        
        Account patientGivingRating = modelAccountSystem.getLoggedInAccount();
        PatientFeedback patientFeedback = new PatientFeedback(patientGivingRating, fiveStarRating, message);        
        
        DoctorRating doctorRating = findDoctorRating(doctorToRate);
        
        // New doctor rating is created if doctor received no feedback previously
        // Otherwise patient feedback is added alongside other patient feedback
        if (doctorRating == null) {
            doctorRating = new DoctorRating(doctorToRate, patientFeedback);
            ratedDoctors.add(doctorRating);
        }
        else
        {
            doctorRating.addPatientFeedback(patientFeedback);
        }      
        
        onUpdateDoctorRatings.invoke();
        
        modelAccountHistoryTracker.recordAction("Submitted patient feedback for doctor " + doctorToRate.getUser().getName() + " "  
        + doctorToRate.getUser().getSurname());
        
        // Feedback recorded successfully
        return true;
    }
    
    public ArrayList<DoctorRating> getRatedDoctors()
    {
        return ratedDoctors;
    }
    
    // Doctor rating needs to be wiped if admin removes doctor account from the system
    public void removeRatedDoctor(Account doctorToRemove) {
        
        DoctorRating doctorRating = findDoctorRating(doctorToRemove);
        
        // Doctor has no rating anyway
        if (doctorRating == null) return;
        
        modelAccountHistoryTracker.recordAction("Removed doctor " + doctorToRemove.getUser().getName() + " "  
        + doctorToRemove.getUser().getSurname() + " ratings from the system");
        
        ratedDoctors.remove(doctorRating);   
        
        onUpdateDoctorRatings.invoke();
    }
    
    // Checks if doctor has received ratings before
    public DoctorRating findDoctorRating(Account doctor){
        
        for (DoctorRating ratedDoctor : ratedDoctors)
        {
            if (ratedDoctor.getDoctorAccount() == doctor)
                return ratedDoctor;
        }
        
        return null;
    }
}
