/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.DoctorRatingSystem.PatientFeedback;
import PatientManagementSystem.Model.Data.DoctorRatingSystem.DoctorRating;
import PatientManagementSystem.Model.User.User;
import java.util.ArrayList;
import PatientManagementSystem.Event;
import PatientManagementSystem.IObserverType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Shem
 */
public class ModelDoctorRatingSystem implements IObserverType<String> {
    
    File file = new File("DoctorRatingSystemData.txt");
    
    // Data to save
    ArrayList<DoctorRating> ratedDoctors = new ArrayList(); 
    
    public Event onUpdateDoctorRatings = new Event();
    
    private final ModelAccountSystem modelAccountSystem;
    private final ModelAccountHistoryTracker modelAccountHistoryTracker;

    public ModelDoctorRatingSystem(ModelAccountSystem modelAccountSystem, ModelAccountHistoryTracker modelAccountHistoryTracker) {
        this.modelAccountSystem = modelAccountSystem;
        modelAccountSystem.onRemoveAccount.addObserver(this);
        this.modelAccountHistoryTracker = modelAccountHistoryTracker;
        
        loadData();
    }   
    
    public void saveData() {
        JSONObject root = new JSONObject();     
        
        JSONArray doctorRatingsArray = new JSONArray();
        
        for(int i = 0; i < ratedDoctors.size(); i++)
        {
            DoctorRating rating = ratedDoctors.get(i);
            
            String doctorId = rating.getDoctorId();
            String summary = rating.getFeedbackSummary();
            String averageFiveStarRating = Integer.toString(rating.getAverageFiveStarRating());
            
            JSONObject ratingObject = new JSONObject();
            
            ratingObject.put("doctorId", doctorId);
            ratingObject.put("summary", summary);
            ratingObject.put("averageFiveStarRating", averageFiveStarRating);
            
            JSONArray feedbackArray = new JSONArray();
            
            ArrayList<PatientFeedback> allFeedback = rating.getAllPatientFeedback();
            
            for (int j = 0; j < allFeedback.size(); j++)
            {
                PatientFeedback feedback = allFeedback.get(j);
                
                JSONObject feedbackObject = new JSONObject();
                
                String patientId = feedback.getPatientId();
                String message = feedback.getMessage();
                String fiveStarRating = Integer.toString(feedback.getDoctorFiveStarRating());
                
                feedbackObject.put("patientId", patientId);
                feedbackObject.put("message", message);
                feedbackObject.put("fiveStarRating", fiveStarRating);
                
                feedbackArray.add(feedbackObject);
            }
            
            ratingObject.put("allFeedback", feedbackArray);
            
            doctorRatingsArray.add(ratingObject);
        }        
        
        root.put("doctorRatings", doctorRatingsArray);
        
        
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(root.toJSONString());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void loadData() {
        StringBuilder jsonIn = new StringBuilder();    
        
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine())
            {
                jsonIn.append(reader.nextLine());
            }
        }            
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());        }
        
        try {              
        JSONParser parser = new JSONParser();        
        JSONObject objRoot = (JSONObject)parser.parse(jsonIn.toString());       
        
        JSONArray doctorRatingsArray = (JSONArray)objRoot.get("doctorRatings");
        
        ratedDoctors = new ArrayList();
        
        for (int i = 0; i < doctorRatingsArray.size(); i++)
        {
            JSONObject objRating = (JSONObject)doctorRatingsArray.get(i);
            
            String doctorId = (String)objRating.get("doctorId");
            String summary = (String)objRating.get("summary");
            int averageFiveStarRating = Integer.parseInt((String)objRating.get("averageFiveStarRating"));
            
            JSONArray allFeedbackArray = (JSONArray)objRating.get("allFeedback");
            
            ArrayList<PatientFeedback> allFeedback = new ArrayList();
            
            for(int j = 0; j < allFeedbackArray.size(); j++)
            {
                JSONObject objFeedback = (JSONObject)allFeedbackArray.get(j);
                
                String patientId = (String)objFeedback.get("patientId");
                int fiveStarRating = Integer.parseInt((String)objFeedback.get("fiveStarRating"));
                String message = (String)objFeedback.get("message");
                
                PatientFeedback feedback = new PatientFeedback(patientId, fiveStarRating, message);
                
                allFeedback.add(feedback);
            }
            
            DoctorRating doctorRating = new DoctorRating(doctorId, allFeedback, averageFiveStarRating, summary);
            
            ratedDoctors.add(doctorRating);
        }        
        }
        catch (ParseException ex)
        {
            System.out.println(ex.toString());
        }
        
    }
    
    // Add feedback from patient to a specific doctor
    public boolean addPatientFeedback(Account doctorToRate, int fiveStarRating, String message)
    {        
        String patientGivingRating = modelAccountSystem.getLoggedInAccount().getId();
        PatientFeedback patientFeedback = new PatientFeedback(patientGivingRating, fiveStarRating, message);        
        
        DoctorRating doctorRating = findDoctorRating(doctorToRate.getId());
        
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
        
        saveData();
        
        // Feedback recorded successfully
        return true;
    }
    
    public ArrayList<DoctorRating> getRatedDoctors()
    {
        return ratedDoctors;
    }
    
    // Doctor rating needs to be wiped if admin removes doctor account from the system
    public void removeRatedDoctor(String doctorId) {
        
        DoctorRating doctorRating = findDoctorRating(doctorId);
        
        // Doctor has no rating anyway
        if (doctorRating == null) return;
        
        User doctor = modelAccountSystem.getAccount(doctorId).getUser();
        
        modelAccountHistoryTracker.recordAction("Removed doctor " + doctor.getName() + " "  
        + doctor.getSurname() + " ratings from the system");
        
        ratedDoctors.remove(doctorRating);   
        
        onUpdateDoctorRatings.invoke();
        
        saveData();
    }
    
    public void removePatientRatings(String patientId)
    {
        for(var rating : ratedDoctors)
        {
            ArrayList<PatientFeedback> feedbackToRemove = new ArrayList();            
            
            for (var feedback : rating.getAllPatientFeedback())
            {
                if (feedback.getPatientId().compareTo(patientId) == 0)
                {
                    feedbackToRemove.add(feedback);
                }
            }
            
            rating.getAllPatientFeedback().removeAll(feedbackToRemove);
        }
        
        modelAccountHistoryTracker.recordAction("Removed all doctor feedback  from patient with id " + patientId
                + " on the system");
        
        onUpdateDoctorRatings.invoke();
        
        saveData();
    }
    
    // Checks if doctor has received ratings before
    public DoctorRating findDoctorRating(String doctorId){
        
        for (DoctorRating ratedDoctor : ratedDoctors)
        {
            if (ratedDoctor.getDoctorId().compareTo(doctorId) == 0)
                return ratedDoctor;
        }
        
        return null;
    }
    
    @Override
    public void update(String id)
    {
        removePatientRatings(id);
        removeRatedDoctor(id);
    }
}
