/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.BookingSystem.Appointment;
import PatientManagementSystem.Model.User.User;
import PatientManagementSystem.View.EventSystem.Event;
import PatientManagementSystem.View.EventSystem.IObserverType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Shem
 */
public class ModelBookingSystem implements IObserverType<String> {
    
    private File file = new File("BookingSystemData.txt");
    
    public Event onUpdateAppointments = new Event();
    
    // Data to save
    private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    
    private final ModelAccountHistoryTracker modelAccountHistoryTracker;
    private final ModelAccountSystem modelAccountSystem;

    public ModelBookingSystem(ModelAccountHistoryTracker modelAccountHistoryTracker, ModelAccountSystem modelAccountSystem) {
        this.modelAccountHistoryTracker = modelAccountHistoryTracker;
        this.modelAccountSystem = modelAccountSystem;
        modelAccountSystem.onRemoveAccount.addObserver(this);
        
        loadData();
    }
    
    private void saveData() {
        JSONObject root = new JSONObject();     
        
        JSONArray appointmentsArray = new JSONArray();
        
        for(int i = 0; i < appointments.size(); i++)
        {
            Appointment appointment = appointments.get(i);
            
            JSONObject appointmentObject = new JSONObject();
            
            appointmentObject.put("patientId", appointment.getPatientId());
            appointmentObject.put("doctorId", appointment.getDoctorId());
            appointmentObject.put("dateAndTime", appointment.getScheduledDateAndTime());
            appointmentObject.put("doctorNotes", appointment.getDoctorNotes());
            
            appointmentsArray.add(appointmentObject);
        }
        
        root.put("appointments", appointmentsArray);
        
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(root.toJSONString());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }
    
    private void loadData() {
        
        StringBuilder jsonIn = new StringBuilder();    
        
        try (java.util.Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine())
            {
                jsonIn.append(reader.nextLine());
            }
        }            
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
        
        try {              
        JSONParser parser = new JSONParser();        
        JSONObject objRoot = (JSONObject)parser.parse(jsonIn.toString());
        
        JSONArray appointmentsArray = (JSONArray)objRoot.get("appointments");
        
        appointments = new ArrayList();
        
        for (int i = 0; i < appointmentsArray.size(); i++)
        {
            JSONObject objAppointment = (JSONObject)appointmentsArray.get(i);
            
            String doctorId = (String)objAppointment.get("doctorId");
            String patientId = (String)objAppointment.get("patientId");
            String dateAndTime = (String)objAppointment.get("dateAndTime");
            String doctorNotes = (String)objAppointment.get("doctorNotes");
            
            Appointment appointment = new Appointment(patientId, doctorId, dateAndTime, doctorNotes);   
            
            appointments.add(appointment);
        }        
        
        }
        catch (ParseException ex)
        {
            System.out.println(ex.toString());
        }
        
    }
    
    public void bookAppointment(String patientId, String doctorId, String dateAndTime) {
                
        Appointment appointment = new Appointment(patientId, doctorId, dateAndTime, "");
        
        appointments.add(appointment);
        
        User patient = modelAccountSystem.getAccount(patientId).getUser();
        User doctor = modelAccountSystem.getAccount(doctorId).getUser();
        
        modelAccountHistoryTracker.recordAction("Booked appointment for patient " + patient.getName() + " "
        + patient.getSurname() + " with doctor " + doctor.getName() + " " + doctor.getSurname());
        
        saveData();
        
        onUpdateAppointments.invoke();
    }
    
    public ArrayList<Appointment> getAppointmentsWithDoctor(Account doctorAccount) {
        
        ArrayList<Appointment> appointmentsWithDoctor = new ArrayList();
        
        for (Appointment appointment : appointments)
        {
            if (appointment.getDoctorId().compareTo(doctorAccount.getId()) == 0)
            {
                appointmentsWithDoctor.add(appointment);
            }
        }
        
        return appointmentsWithDoctor;
    }
    
    public ArrayList<Appointment> getAppointmentsForPatient(Account patient)
    {
        ArrayList<Appointment> appointmentsWithPatient = new ArrayList();
        
        for (Appointment appointment : appointments)
        {
            if (appointment.getPatientId().compareTo(patient.getId()) == 0)
            {
                appointmentsWithPatient.add(appointment);
            }
        }
        
        return appointmentsWithPatient;
    }
    
    public ArrayList<Appointment> getAllAppointments() 
    {
        return appointments;
    }
    
    public void removeAppointmentsForPatient(String patientId)
    {
        ArrayList<Appointment> appointmentsToRemove = new ArrayList();
        
        for (var appointment : appointments)
        {
            if (appointment.getPatientId().compareTo(patientId) == 0)
            {
                appointmentsToRemove.add(appointment);
            }
        }
        
        appointments.removeAll(appointmentsToRemove);
        
        saveData();      
        
        onUpdateAppointments.invoke();
    }
    
    public void removeAppointmentsWithDoctor(String doctorId)
    {
        ArrayList<Appointment> appointmentsToRemove = new ArrayList();
        
        for (var appointment : appointments)
        {
            if (appointment.getDoctorId().compareTo(doctorId) == 0)
            {
                appointmentsToRemove.add(appointment);
            }
        }
        
        appointments.removeAll(appointmentsToRemove);
        
        saveData();      
        
        onUpdateAppointments.invoke();
    }
    
    public void addNotes(Appointment appointment, String notes) {
        
        appointment.setDoctorNotes(notes);
        saveData();
        
        modelAccountHistoryTracker.recordAction("Made notes for appointment " + appointment.getScheduledDateAndTime() + " with patient "
         + appointment.getPatientId());
        
        onUpdateAppointments.invoke();
    }
    
    @Override
    public void update(String id)
    {
        removeAppointmentsWithDoctor(id);
        removeAppointmentsForPatient(id);
    }
}
