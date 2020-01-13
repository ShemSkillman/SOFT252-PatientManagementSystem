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
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ModelPrescriptionSystem {
    
    public Event onUpdateMedicineStock = new Event();
    
    ArrayList<Medicine> medicineStock = new ArrayList();
    
    ArrayList<PatientMedicineRecord> patientRecords = new ArrayList();
    
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
