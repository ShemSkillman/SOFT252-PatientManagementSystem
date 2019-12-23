/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientmanagementsystem.model.rating;

import java.util.ArrayList;
import patientmanagementsystem.user.*;

/**
 *
 * @author Shem
 */
public class DoctorRating {
    private Doctor doctor;       
        private int averageFiveStarRating = 0, totalStars = 0;
        private String feedbackSummary;
        private ArrayList<PatientFeedback> allPatientFeedback = new ArrayList<PatientFeedback>(); 
        
        public DoctorRating(Doctor doctor, PatientFeedback patientFeedback){
            this.doctor = doctor;
            addPatientFeedback(patientFeedback);
        }
        
        public void addPatientFeedback(PatientFeedback newFeedback){
            
            Patient patient = newFeedback.getPatient();
            
            // Prevents duplicate feedback from same patient 
            // Deletes old feedback and replaces it with new
            for(PatientFeedback patientFeedback : allPatientFeedback)
            {
                if (patientFeedback.getPatient() == patient){
                    allPatientFeedback.remove(patientFeedback);
                    break;
                }
            }
            
            allPatientFeedback.add(newFeedback);
            updateAverageStars();
        }
        
        private void updateAverageStars() {
            
            int totalStars = 0;
            
            for(PatientFeedback feedback : allPatientFeedback) {
                totalStars += feedback.getDoctorFiveStarRating();
            }
            
            averageFiveStarRating = totalStars / allPatientFeedback.size();
        }

        public Doctor getDoctor() {
            return doctor;
        }

        public int getAverageFiveStarRating() {
            return averageFiveStarRating;
        }

        public String getFeedbackSummary() {
            return feedbackSummary;
        }

        public ArrayList<PatientFeedback> getAllPatientFeedback() {
            return allPatientFeedback;
        }

        public void setFeedbackSummary(String feedbackSummary) {
            this.feedbackSummary = feedbackSummary;
        }
}
