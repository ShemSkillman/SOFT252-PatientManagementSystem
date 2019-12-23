/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientmanagementsystem.model;
import java.util.ArrayList;
import patientmanagementsystem.user.*;
import patientmanagementsystem.model.rating.*;

/**
 *
 * @author Shem
 */
public class ModelDoctorRatingSystem {
    
    ArrayList<DoctorRating> ratedDoctors = new ArrayList<DoctorRating>();
    
    // Add feedback from patient to a specific doctor
    public boolean addPatientFeedback(Patient patientGivingRating, Doctor doctorToRate, int fiveStarRating, String message)
    {        
        PatientFeedback patientFeedback = new PatientFeedback(patientGivingRating, fiveStarRating, message);        
        
        DoctorRating doctorRating = FindDoctorRating(doctorToRate);
        
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
        
        // Feedback recorded successfully
        return true;
    }
    
    // Doctor rating needs to be wiped if admin removes doctor account from the system
    public void removeRatedDoctor(Doctor doctorToRemove) {
        
        DoctorRating doctorRating = FindDoctorRating(doctorToRemove);
        
        // Doctor has no rating anyway
        if (doctorRating == null) return;
        
        ratedDoctors.remove(doctorRating);    
    }
    
    // Checks if doctor has received ratings before
    private DoctorRating FindDoctorRating(Doctor doctor){
        
        for (DoctorRating ratedDoctor : ratedDoctors)
        {
            if (ratedDoctor.getDoctor() == doctor)
                return ratedDoctor;
        }
        
        return null;
    }
}
