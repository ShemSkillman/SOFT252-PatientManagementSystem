/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.DoctorRatingSystem.DoctorRating;
import PatientManagementSystem.Model.Data.DoctorRatingSystem.PatientFeedback;
import PatientManagementSystem.Model.User.Doctor;
import PatientManagementSystem.Model.User.Gender;
import PatientManagementSystem.Model.User.Patient;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Shem
 */
public class ModelDoctorRatingSystemTest {
    
    ModelDoctorRatingSystem modelDoctorRatingSystem;
    ModelAccountSystem modelAccountSystem;
    ModelAccountHistoryTracker modelAccountHistoryTracker;
    Account doctorAccount;
    Account loggedInPatientAccount;
    
    public ModelDoctorRatingSystemTest() {
    }
    
    @BeforeEach
    public void setUp() {
        
        modelAccountHistoryTracker = new ModelAccountHistoryTracker();
        modelAccountSystem = new ModelAccountSystem(modelAccountHistoryTracker);
        modelAccountHistoryTracker.setModelAccountSystem(modelAccountSystem);
        
        modelDoctorRatingSystem = new ModelDoctorRatingSystem(modelAccountSystem, modelAccountHistoryTracker);
        
        Patient patient = new Patient("", "", "", 10, Gender.Male);
        loggedInPatientAccount = modelAccountSystem.CreateAccount(patient, "123");
        modelAccountSystem.logIn(loggedInPatientAccount.getId(), loggedInPatientAccount.getPassword());        
        
        Doctor doctor = new Doctor("", "", "");
        doctorAccount = modelAccountSystem.CreateAccount(doctor, "123");
    }

    @Test
    public void testAddPatientFeedback() {
        
        System.out.println("Testing adding patient feedback");
        
        modelDoctorRatingSystem.addPatientFeedback(doctorAccount, 5, "really good doctor");
        modelDoctorRatingSystem.addPatientFeedback(doctorAccount, 3, "average doctor");
        modelDoctorRatingSystem.addPatientFeedback(doctorAccount, 1, "extremely terrible doctor!");
        
        DoctorRating rating = modelDoctorRatingSystem.findDoctorRating(doctorAccount.getId());
        
        assertEquals(rating.getAverageFiveStarRating(), 3);
        
        ArrayList<PatientFeedback> allFeedback = rating.getAllPatientFeedback();
        
        PatientFeedback feedbackOne = allFeedback.get(0);
        PatientFeedback feedbackTwo = allFeedback.get(1);
        PatientFeedback feedbackThree = allFeedback.get(2);
        
        assertEquals(feedbackOne.getMessage(), "really good doctor");
        assertEquals(feedbackOne.getDoctorFiveStarRating(), 5);
        
        assertEquals(feedbackTwo.getMessage(), "average doctor");
        assertEquals(feedbackTwo.getDoctorFiveStarRating(), 3);
        
        assertEquals(feedbackThree.getMessage(), "extremely terrible doctor!");
        assertEquals(feedbackThree.getDoctorFiveStarRating(), 1);
    }

    @Test
    public void testAddDoctorSummary() {
        
        System.out.println("Testing adding doctor summary");
        
        modelDoctorRatingSystem.addPatientFeedback(doctorAccount, 5, "really good doctor");
        modelDoctorRatingSystem.addPatientFeedback(doctorAccount, 3, "average doctor");
        modelDoctorRatingSystem.addPatientFeedback(doctorAccount, 1, "extremely terrible doctor!");
        
        DoctorRating rating = modelDoctorRatingSystem.findDoctorRating(doctorAccount.getId());
        
        modelDoctorRatingSystem.addDoctorSummary(doctorAccount.getId(), "pretty average doctor");
        
        assertEquals(rating.getFeedbackSummary(), "pretty average doctor");
        
        modelDoctorRatingSystem.addDoctorSummary(doctorAccount.getId(), "OKAY doctor");
        
        assertEquals(rating.getFeedbackSummary(), "OKAY doctor");
        
        modelDoctorRatingSystem.addDoctorSummary(doctorAccount.getId(), "meh doctor");
        
        assertEquals(rating.getFeedbackSummary(), "meh doctor");
    }

    @Test
    public void testRemoveRatedDoctor() {
        
        System.out.println("Testing adding doctor summary");
        
        modelDoctorRatingSystem.addPatientFeedback(doctorAccount, 5, "really good doctor");
        
        modelDoctorRatingSystem.removeRatedDoctor(doctorAccount.getId());
        
        assertNull(modelDoctorRatingSystem.findDoctorRating(doctorAccount.getId()));
    }

    @Test
    public void testRemovePatientRatings() {
        
        System.out.println("Testing removing patient ratings");
        
        Doctor doctor = new Doctor("", "", "");
        Account doctorAccountTwo = modelAccountSystem.CreateAccount(doctor, "123");
        Account doctorAccountThree = modelAccountSystem.CreateAccount(doctor, "123");
        
        modelDoctorRatingSystem.addPatientFeedback(doctorAccount, 5, "really good doctor");
        modelDoctorRatingSystem.addPatientFeedback(doctorAccountTwo, 3, "average doctor");
        modelDoctorRatingSystem.addPatientFeedback(doctorAccountThree, 1, "extremely terrible doctor!");
        
        modelDoctorRatingSystem.removePatientRatings(loggedInPatientAccount.getId());
        
        DoctorRating ratingOne = modelDoctorRatingSystem.findDoctorRating(doctorAccount.getId());
        DoctorRating ratingTwo = modelDoctorRatingSystem.findDoctorRating(doctorAccountTwo.getId());
        DoctorRating ratingThree = modelDoctorRatingSystem.findDoctorRating(doctorAccountThree.getId());
        
        assertTrue(ratingOne.getAllPatientFeedback().size() < 1);
        assertTrue(ratingTwo.getAllPatientFeedback().size() < 1);
        assertTrue(ratingThree.getAllPatientFeedback().size() < 1);
    }

    @Test
    public void testFindDoctorRating() {
        
        System.out.println("Testing finding doctor rating");
        
        Doctor doctor = new Doctor("", "", "");
        Account doctorAccountTwo = modelAccountSystem.CreateAccount(doctor, "123");
        Account doctorAccountThree = modelAccountSystem.CreateAccount(doctor, "123");
        
        modelDoctorRatingSystem.addPatientFeedback(doctorAccount, 5, "really good doctor");
        modelDoctorRatingSystem.addPatientFeedback(doctorAccountTwo, 3, "average doctor");
        modelDoctorRatingSystem.addPatientFeedback(doctorAccountThree, 1, "extremely terrible doctor!");
        
        assertNotNull(modelDoctorRatingSystem.findDoctorRating(doctorAccount.getId()));
        assertNotNull(modelDoctorRatingSystem.findDoctorRating(doctorAccountTwo.getId()));
        assertNotNull(modelDoctorRatingSystem.findDoctorRating(doctorAccountThree.getId()));
        assertNull(modelDoctorRatingSystem.findDoctorRating("hgutjr"));
        assertNull(modelDoctorRatingSystem.findDoctorRating("S9999"));
        assertNull(modelDoctorRatingSystem.findDoctorRating(""));
    }
    
}
