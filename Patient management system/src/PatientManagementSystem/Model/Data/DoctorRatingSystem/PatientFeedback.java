/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.DoctorRatingSystem;
import PatientManagementSystem.Model.Data.AccountSystem.Account;

/**
 *
 * @author Shem
 */
public class PatientFeedback {
    private Account patientAccount;
    private int doctorFiveStarRating;
    private String message;
    
    public PatientFeedback(Account patientAccount, int doctorFiveStarRating, String message) {
        this.patientAccount = patientAccount;
        this.doctorFiveStarRating = doctorFiveStarRating;
        this.message = message;
    }
    
    public Account getPatientAccount(){
        return patientAccount;
    }

    public int getDoctorFiveStarRating() {
        return doctorFiveStarRating;
    }

    public String getMessage() {
        return message;
    }

    public void setDoctorFiveStarRating(int doctorFiveStarRating) {
        this.doctorFiveStarRating = doctorFiveStarRating;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
