/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.PatientRequestSystem.AccountRequest;
import PatientManagementSystem.Model.Data.PatientRequestSystem.AppointmentRequest;
import PatientManagementSystem.Model.Data.PatientRequestSystem.TerminationRequest;
import PatientManagementSystem.Model.ICommand;
import PatientManagementSystem.Model.ModelMain;
import java.util.ArrayList;
import PatientManagementSystem.Model.User.*;
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
public class ModelPatientRequestSystem implements IObserverType<String> {
    
    private final ArrayList<ICommand> requests = new ArrayList<ICommand>();
    
    File file = new File("PatientRequestSystemData.txt");
    
    public Event onUpdateRequests = new Event();
    
    private final ModelAccountSystem modelAccountSystem;
    private final ModelBookingSystem modelBookingSystem;
    private final ModelAccountHistoryTracker modelAccountHistoryTracker;
    private final ModelMain modelMain;

    public ModelPatientRequestSystem(ModelAccountSystem modelAccountSystem, ModelBookingSystem modelBookingSystem, 
            ModelAccountHistoryTracker modelAccountHistoryTracker, ModelMain modelMain) {
        this.modelAccountSystem = modelAccountSystem;
        modelAccountSystem.onRemoveAccount.addObserver(this);
        this.modelBookingSystem = modelBookingSystem;
        this.modelAccountHistoryTracker = modelAccountHistoryTracker;
        this.modelMain = modelMain;
        
        loadData();
    }   
    
    public void saveData() {
        JSONObject root = new JSONObject();     
        
        JSONArray requestsArray = new JSONArray();
        
        for(int i = 0; i < requests.size(); i++)
        {
            ICommand request = requests.get(i);
            
            JSONObject requestObject = new JSONObject();
            
            
            if (request instanceof AccountRequest){
                
                JSONObject patientObject = new JSONObject();
                
                AccountRequest accountRequest = (AccountRequest)request;
                Patient patient = accountRequest.getPatientDetails();
                
                patientObject.put("patientFirstName", patient.getName());
                patientObject.put("patientSurname", patient.getSurname());
                patientObject.put("patientAddress", patient.getAddress());
                patientObject.put("patientAge", Integer.toString(patient.getAge()));
                patientObject.put("patientGender", patient.getGender().toString());
                
                requestObject.put("password", accountRequest.getPassword());
                requestObject.put("patientDetails", patientObject);                
                
                requestObject.put("requestType", "accountRequest");
            }
            else if (request instanceof AppointmentRequest) {
                
                AppointmentRequest appointmentRequest = (AppointmentRequest)request;
                
                requestObject.put("patientId", appointmentRequest.getPatientId());
                requestObject.put("doctorId", appointmentRequest.getDoctorId());
                requestObject.put("timeAndDate", appointmentRequest.getDateAndTime());
                
                requestObject.put("requestType", "appointmentRequest");
            }
            else if (request instanceof TerminationRequest){
                
                TerminationRequest terminationRequest = (TerminationRequest)request;
                
                requestObject.put("reason", terminationRequest.getReason());
                requestObject.put("patientId", terminationRequest.getPatientId());
                
                requestObject.put("requestType", "terminationRequest");
            }
            
            requestsArray.add(requestObject);
        }
        
        root.put("requests", requestsArray);
        
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
        
        JSONArray requestsArray = (JSONArray)objRoot.get("requests");
        
        for (int i = 0; i < requestsArray.size(); i++)
        {
            JSONObject objRequest = (JSONObject)requestsArray.get(i);
            
            ICommand request = null;
            
            switch((String)objRequest.get("requestType"))
            {
                case "accountRequest":
                    JSONObject objPatientDetails = (JSONObject)objRequest.get("patientDetails");
                    
                    String patientFirstName = (String)objPatientDetails.get("patientFirstName");
                    String patientSurname = (String)objPatientDetails.get("patientSurname");
                    String patientAddress = (String)objPatientDetails.get("patientAddress");
                    int patientAge = Integer.parseInt((String)objPatientDetails.get("patientAge"));
                    Gender patientGender = Gender.valueOf((String)objPatientDetails.get("patientGender"));
                    
                    String password = (String)objRequest.get("password");
                    
                    Patient patient = new Patient(patientFirstName, patientSurname, patientAddress, patientAge, patientGender);
                    
                    request = new AccountRequest(patient, password, modelAccountSystem);
                    break;
                case "appointmentRequest":                    
                    String patientId = (String)objRequest.get("patientId");
                    String doctorId = (String)objRequest.get("doctorId");
                    String dateAndTime = (String)objRequest.get("timeAndDate");
                    
                    request = new AppointmentRequest(patientId, dateAndTime, doctorId, modelMain);
                    break;
                case "terminationRequest":
                    String reason = (String)objRequest.get("reason");
                    String patientId2 = (String)objRequest.get("patientId");
                    
                    request = new TerminationRequest(patientId2, reason, modelAccountSystem);
                    break;
                    
            }
            
            requests.add(request);
        }
        
        }
        catch (ParseException ex)
        {
            System.out.println(ex.toString());
        }
    }
    
    public void requestAccount(Patient requestingPatient, String password) {
        
        AccountRequest accountRequest = new AccountRequest(requestingPatient, password, modelAccountSystem);
        requests.add(accountRequest);
        
        onUpdateRequests.invoke();
        
        saveData();
    }
    
    public void requestAppointment(Account fromPatient, String dateAndTime, Account withDoctor) {
        
        AppointmentRequest appointmentRequest = new AppointmentRequest(fromPatient.getId(), dateAndTime, withDoctor.getId(), modelMain);
        
        requests.add(appointmentRequest);
        
        modelAccountHistoryTracker.recordAction("Requested appointment with doctor " + withDoctor.getUser().getName() + " " + withDoctor.getUser().getSurname());
        
        onUpdateRequests.invoke();
        
        saveData();
    }
    
    public void requestAccountTermination(Account patientAccount, String reason) {
        
        TerminationRequest terminationRequest = new TerminationRequest(patientAccount.getId(), reason, modelAccountSystem);
        
        requests.add(terminationRequest);
        
        modelAccountHistoryTracker.recordAction("Requested termination of account");
        
        onUpdateRequests.invoke();
        
        saveData();
    }
    
    public void approveRequest(ICommand request) {
        request.execute();
        if (requests.contains(request)) requests.remove(request);
        
        onUpdateRequests.invoke();
        
        saveData();
    }
    
    public void rejectRequest(ICommand request) {
        if (requests.contains(request)) requests.remove(request);
        
        onUpdateRequests.invoke();
        
        saveData();
    }
    
    public void removeRequestsWithPatientId(String patientId)
    {
        ArrayList<ICommand> requestsToRemove = new ArrayList();
        
        for (int i = 0; i < requests.size(); i++)
        {
            ICommand request = requests.get(i);
            
            String id = request.getSenderId();
            if (id == null) continue;
            
            if (id.compareTo(patientId) == 0)
            {
                requestsToRemove.add(request);
            }
        }
        
        requests.removeAll(requestsToRemove);
        
        saveData();
    }
    
    public ArrayList<ICommand> getAllRequests() {
        return requests;
    }
    
    @Override
    public void update(String id)
    {
        removeRequestsWithPatientId(id);
    }
}
