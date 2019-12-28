/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;
import PatientManagementSystem.Model.User.Doctor;
import PatientManagementSystem.Model.User.Patient;
import PatientManagementSystem.Model.Data.DoctorRatingSystem.PatientFeedback;
import PatientManagementSystem.Model.Data.DoctorRatingSystem.DoctorRating;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import java.util.ArrayList;
import PatientManagementSystem.Model.User.*;

/**
 *
 * @author Shem
 */
public class ModelDoctorRatingSystem {
    
    ArrayList<DoctorRating> ratedDoctors = new ArrayList<DoctorRating>();    
    ArrayList<Account> doctorAccounts = new ArrayList<Account>();
    
    private final ModelAccountSystem modelAccountSystem;

    public ModelDoctorRatingSystem(ModelAccountSystem modelAccountSystem) {
        this.modelAccountSystem = modelAccountSystem;
    }   
    
    // Add feedback from patient to a specific doctor
    public boolean addPatientFeedback(Patient patientGivingRating, Doctor doctorToRate, int fiveStarRating, String message)
    {        
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
        
        // Feedback recorded successfully
        return true;
    }
    
    public ArrayList<String> getDoctorNames() {
        
        doctorAccounts = modelAccountSystem.getAccountsOfTypeRole(Role.Doctor);
        
        ArrayList<String> doctorNames = new ArrayList<String>();
        
        for(Account doctorAccount : doctorAccounts) 
        {
            String doctorName = doctorAccount.getId() + " " + doctorAccount.getUser().getName() + 
                    " " + doctorAccount.getUser().getSurname();
            doctorNames.add(doctorName);
        }
        
        return doctorNames;
    }
    
    public Account getDoctorAccount(int index) {
        
        if (doctorAccounts == null) return null;
        
        return doctorAccounts.get(index);
    }
    
    // Doctor rating needs to be wiped if admin removes doctor account from the system
    public void removeRatedDoctor(Doctor doctorToRemove) {
        
        DoctorRating doctorRating = findDoctorRating(doctorToRemove);
        
        // Doctor has no rating anyway
        if (doctorRating == null) return;
        
        ratedDoctors.remove(doctorRating);    
    }
    
    // Checks if doctor has received ratings before
    public DoctorRating findDoctorRating(Doctor doctor){
        
        for (DoctorRating ratedDoctor : ratedDoctors)
        {
            if (ratedDoctor.getDoctor() == doctor)
                return ratedDoctor;
        }
        
        return null;
    }
}
