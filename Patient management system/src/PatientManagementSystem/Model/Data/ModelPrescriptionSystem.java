/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;

import PatientManagementSystem.Model.Data.PrescriptionSystem.Medicine;
import PatientManagementSystem.Model.Data.PrescriptionSystem.PatientMedicineRecord;
import PatientManagementSystem.Model.Data.PrescriptionSystem.Prescription;
import PatientManagementSystem.View.EventSystem.Event;
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
public class ModelPrescriptionSystem {
    
    public Event onUpdateMedicineStock = new Event();
    
    private File file = new File("PrescriptionSystemData.txt");
    
    // Data to save
    ArrayList<Medicine> medicineStock = new ArrayList();    
    ArrayList<PatientMedicineRecord> patientRecords = new ArrayList();
    
    private void saveData() {
        JSONObject root = new JSONObject();     
        
        JSONArray medicineStockArray = new JSONArray();
        
        for (int i = 0; i < medicineStock.size(); i++)
        {
            Medicine medicine = medicineStock.get(i);
            
            JSONObject medicineObject = new JSONObject();
            
            String medicineName = medicine.getName();
            String medicineQuantity = Integer.toString(medicine.getQuantity());
            
            medicineObject.put("medicineName", medicineName);
            medicineObject.put("medicineQuantity", medicineQuantity);
            
            medicineStockArray.add(medicineObject);
        }
        
        root.put("medicineStock", medicineStockArray);
        
        JSONArray patientRecordsArray = new JSONArray();
        
        for (int i = 0; i < patientRecords.size(); i++)
        {
            PatientMedicineRecord patientRecord = patientRecords.get(i);
            JSONObject patientRecordObject = new JSONObject();
            
            patientRecordObject.put("patientId", patientRecord.getPatientId());
            
            ArrayList<Prescription> prescriptions = patientRecord.getPrescriptions();
            
            JSONArray prescriptionsArray = new JSONArray();
            
            for (int j = 0; j < prescriptions.size(); j++)
            {
                Prescription prescription = prescriptions.get(j);
                JSONObject prescriptionObject = new JSONObject();
                
                prescriptionObject.put("medicineName", prescription.getMedicineName());
                prescriptionObject.put("medicineQuantity", prescription.getMedicineQuantity());
                prescriptionObject.put("medicineDosage", prescription.getDosage());
                prescriptionObject.put("dateGiven", prescription.getDateGiven());
                
                prescriptionsArray.add(prescriptionObject);
            }
            
            patientRecordObject.put("prescriptions", prescriptionsArray);
            
            patientRecordsArray.add(patientRecordObject);
        }
        
        root.put("patientRecords", patientRecordsArray);
        
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(root.toJSONString());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }    
    
    private void loadData() {
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
        
        JSONArray medicineStockArray = (JSONArray)objRoot.get("medicineStock");
        medicineStock = new ArrayList();
        
        for (int i = 0; i < medicineStockArray.size(); i++)
        {
            JSONObject objMedicine = (JSONObject)medicineStockArray.get(i);
            
            String medicineName = (String)objMedicine.get("medicineName");
            int medicineQuantity = Integer.parseInt((String)objMedicine.get("medicineQuantity"));
            
            Medicine medicine = new Medicine(medicineName, medicineQuantity);
            
            medicineStock.add(medicine);
        }
        
        JSONArray patientRecordsArray = (JSONArray)objRoot.get("patientRecords");
        patientRecords = new ArrayList();
        
        for (int i = 0; i < patientRecordsArray.size(); i++)
        {
            JSONObject objPatientRecord = (JSONObject)patientRecordsArray.get(i);
            String patientId = (String)objPatientRecord.get("patientId");
            
            JSONArray prescriptionsArray = (JSONArray)objPatientRecord.get("prescriptions");
            ArrayList<Prescription> prescriptions = new ArrayList();
            
            for (int j = 0; j < prescriptionsArray.size(); j++)
            {
                JSONObject prescriptionObj = (JSONObject)prescriptionsArray.get(i);
                
                String medicineName = (String)prescriptionObj.get("medicineName");
                int medicineQuantity = Integer.parseInt((String)prescriptionObj.get("medicineQuantity"));
                String medicineDosage = (String)prescriptionObj.get("medicineDosage");
                String medicineDateGiven = (String)prescriptionObj.get("dateGiven");
                
                Prescription prescription = new Prescription(medicineName, medicineQuantity, medicineDosage, medicineDateGiven);
                
                prescriptions.add(prescription);
            }
            
            PatientMedicineRecord patientRecord = new PatientMedicineRecord(patientId, prescriptions);
            
            patientRecords.add(patientRecord);
        }
        
        }
        catch (ParseException ex)
        {
            System.out.println(ex.toString());
        }
    }
    
    public void givePrescription(String patientId, String medicineName, int quantity, String dosage)
    {
        Prescription prescription = new Prescription(medicineName, quantity, dosage);
        
        PatientMedicineRecord medicineRecord = searchRecords(patientId);
        if (medicineRecord == null) {
            medicineRecord = new PatientMedicineRecord(patientId, prescription);
            patientRecords.add(medicineRecord);
        }            
        else {
            medicineRecord.addPrescription(prescription);
        }
            
        consumeMedicine(medicineName, quantity);
        
    }
    
    private PatientMedicineRecord searchRecords(String patientId) {
        
        for (var record : patientRecords)
        {
            if (record.getPatientId().compareTo(patientId) == 0)
            {
                return record;
            }
        }
        
        return null;
    }
    
    public ArrayList<Prescription> getPrescriptionsForPatient(String patientId)
    {
        PatientMedicineRecord record = searchRecords(patientId);
        
        if (record == null) return new ArrayList();
        
        return record.getPrescriptions();
    }
    
    public void createNewMedicine(String medicineName) {
        
        if (lookForMedicine(medicineName) != null) return;
        
        medicineName = medicineName.toLowerCase();
        
        Medicine medicine = new Medicine(medicineName, 0);
        
        medicineStock.add(medicine);        
        
        onUpdateMedicineStock.invoke();
    }
    
    public ArrayList<Medicine> getMedecineStock() {
        return medicineStock;
    }
    
    public ArrayList<String> getMedicineNames() {
        
        ArrayList<String> medicineNames = new ArrayList();
        
        for (var medicine : medicineStock) {
            medicineNames.add(medicine.getName());
        }
        
        return medicineNames;
    }
    
    private Medicine lookForMedicine(String medicineName) {
        
        medicineName = medicineName.toLowerCase();
        
        for (var medicine : medicineStock)
        {
            if (medicine.getName().compareTo(medicineName) == 0)
            {
                return medicine;
            }
        }
        
        return null;
    }
    
    public void restockMedicine(String medicineToRestock, int orderQuantity) {
        
        Medicine medicine = lookForMedicine(medicineToRestock);
        
        medicine.add(orderQuantity);
        
        System.out.println("Medicine restocked: " + medicineToRestock + " new quantity: " + medicine.getQuantity());
        
        onUpdateMedicineStock.invoke();
    }
    
    public void consumeMedicine(String medicineToConsume, int amount) {
        
        Medicine medicine = lookForMedicine(medicineToConsume);
        
        medicine.consume(amount);
        
        onUpdateMedicineStock.invoke();
    }
    
}
