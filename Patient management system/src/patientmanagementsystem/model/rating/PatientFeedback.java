/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientmanagementsystem.model.rating;
import patientmanagementsystem.user.Patient;

/**
 *
 * @author Shem
 */
public class PatientFeedback {
    private Patient patient;
    private int doctorFiveStarRating;
    private String message;
    
    public PatientFeedback(Patient patient, int doctorFiveStarRating, String message) {
        this.patient = patient;
        this.doctorFiveStarRating = doctorFiveStarRating;
        this.message = message;
    }
    
    public Patient getPatient(){
        return patient;
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
