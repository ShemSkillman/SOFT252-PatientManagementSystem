/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.DoctorRatingSystem;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.User.Doctor;
import PatientManagementSystem.Model.User.Patient;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class DoctorRating {
        private Account doctorAccount;       
        private int averageFiveStarRating = 0, totalStars = 0;
        private String feedbackSummary;
        private ArrayList<PatientFeedback> allPatientFeedback = new ArrayList<PatientFeedback>(); 
        
        public DoctorRating(Account doctorAccount, PatientFeedback patientFeedback){
            this.doctorAccount = doctorAccount;
            addPatientFeedback(patientFeedback);
        }
        
        public void addPatientFeedback(PatientFeedback newFeedback){
            
            Account patientAccount = newFeedback.getPatientAccount();
            
            // Prevents duplicate feedback from same patient 
            // Deletes old feedback and replaces it with new
            for(PatientFeedback patientFeedback : allPatientFeedback)
            {
                if (patientFeedback.getPatientAccount() == patientAccount){
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

        public Account getDoctorAccount() {
            return doctorAccount;
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
                if (feedback.getPatientAccount() == patientAccount)
                    return feedback;
            }
            
            return null;
        }
}
