/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.DoctorRatingSystem;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class DoctorRating {
        private String doctorId;       
        private int averageFiveStarRating = 0;
        private String feedbackSummary;
        private ArrayList<PatientFeedback> allPatientFeedback = new ArrayList<PatientFeedback>(); 
        
        public DoctorRating(Account doctorAccount, PatientFeedback patientFeedback){
            this.doctorId = doctorAccount.getId();
            addPatientFeedback(patientFeedback);
        }
        
        public DoctorRating(String doctorId, ArrayList<PatientFeedback> allPatientFeedback, int averageFiveStarRating, String feedbackSummary){
            this.doctorId = doctorId;
            this.allPatientFeedback = allPatientFeedback;
            this.averageFiveStarRating = averageFiveStarRating;
            this.feedbackSummary = feedbackSummary;
        }
        
        public void addPatientFeedback(PatientFeedback newFeedback){
            
            String patientId = newFeedback.getPatientId();
            
            // Prevents duplicate feedback from same patient 
            // Deletes old feedback and replaces it with new
            for(PatientFeedback patientFeedback : allPatientFeedback)
            {
                if (patientFeedback.getPatientId().compareTo(newFeedback.getPatientId()) == 0){
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

        public String getDoctorId() {
            return doctorId;
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
        
        public PatientFeedback getPatientFeedbackForDoctor(Account patientAccount) {
            
            for(PatientFeedback feedback : allPatientFeedback)
            {
                if (feedback.getPatientId().compareTo(patientAccount.getId()) == 0)
                    return feedback;
            }
            
            return null;
        }
}
